package application.components.springSecurity;

import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private String DEFAULT_FAILURE_URL = "/login?error";

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

    }

    private void lockUser(String username) {
        AppUser appUser = appUserService.getAppUserByEmail(username);

        if (appUser != null) {
            int failedCount = appUser.getFailedAttempt() + 1;
            appUser.setFailedAttempt(failedCount);

            if (failedCount > 3) {
                appUser.setAccountNonLocked(false);
            }

            appUserService.updateAppUser(appUser);
            //userService.saveUser(user);
        }
    }
}
