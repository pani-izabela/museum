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
    private static final long LOCK_TIME_DURATION = 2 * 60 * 1000; //5 minut

    @Autowired
    private AppUserService appUserService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        if (exception instanceof BadCredentialsException) {
            exception = lockUser(request.getParameter("email"));
        } else {
            exception = unlockWhenTimeExpired(request.getParameter("email"));
        }
        setDefaultFailureUrl(DEFAULT_FAILURE_URL);
        super.onAuthenticationFailure(request, response, exception);

    }

    private AuthenticationException lockUser(String username) {
        AppUser appUser = appUserService.getAppUserByEmail(username);
        AuthenticationException exception = new LockedException("Zła nazwa użytkownika lub hasło");

        if (appUser != null) {
            if (appUser.getAccountNonLocked()) {
                int failedCount = appUser.getFailedAttempt() + 1;
                appUser.setFailedAttempt(failedCount);

                if (failedCount >= 3) {
                    appUser.setAccountNonLocked(false);
                    appUser.setLockTime(new Date());
                    exception = new LockedException("Twoje konto zostało zablokowane z powodu 3 nieudanych prób logowania. \n Zostanie odblokowane po 5 minutach.");
                }
            }
            appUserService.updateAppUser(appUser);
        }
        return exception;
    }

    private AuthenticationException unlockWhenTimeExpired(String email) {
        AppUser appUser = appUserService.getAppUserByEmail(email);
        if (appUser != null) {
            long lockTimeInMillis = appUser.getLockTime().getTime();
            long currentTimeInMillis = System.currentTimeMillis();

            if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
                appUser.setAccountNonLocked(true);
                appUser.setLockTime(null);
                appUser.setFailedAttempt(0);

                appUserService.updateAppUser(appUser);

                return new LockedException("Twoje konto zostało odblokowane. Spróbuj zalogować się ponownie.");
            }
        }
        return new LockedException("Twoje konto jest nadal zablokowane. Spróbuj zalogować się ponownie za kilka minut.");
    }
}
