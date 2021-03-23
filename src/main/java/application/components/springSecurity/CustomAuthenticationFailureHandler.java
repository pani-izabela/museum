package application.components.springSecurity;

import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private String DEFAULT_FAILURE_URL = "/login?error";
    private static final long LOCK_TIME_DURATION = 5 * 60 * 1000; //5 minut

    @Autowired
    private AppUserService appUserService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        setDefaultFailureUrl(DEFAULT_FAILURE_URL);
        super.onAuthenticationFailure(request, response, exception);

        if (exception instanceof BadCredentialsException) {
            lockUser(request.getParameter("email"));
        }
        else{
            unlockWhenTimeExpired(request.getParameter("email"));
        }

    }

    private Exception lockUser(String username) {
        AppUser appUser = appUserService.getAppUserByEmail(username);
        Exception exception = new Exception();

        if (appUser != null) {
            if(appUser.getAccountNonLocked()){
                int failedCount = appUser.getFailedAttempt() + 1;
                appUser.setFailedAttempt(failedCount);

                if (failedCount >= 3) {
                    appUser.setAccountNonLocked(false);
                    appUser.setLockTime(new Date());
                    exception = new LockedException("Your account has been locked due to 3 failed attempts."
                            + " It will be unlocked after 24 hours.");
                }
            }
            appUserService.updateAppUser(appUser);
        }
        return exception;
    }

    private Exception unlockWhenTimeExpired(String email) {
        AppUser appUser = appUserService.getAppUserByEmail(email);
        long lockTimeInMillis = appUser.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            appUser.setAccountNonLocked(true);
            appUser.setLockTime(null);
            appUser.setFailedAttempt(0);

            appUserService.updateAppUser(appUser);

            return new Exception(new LockedException("Your account has been unlocked. Please try to login again."));
        }

        return  new Exception(new LockedException("Your account is still locked. Please try to login again in a few minutes."));
    }
}
