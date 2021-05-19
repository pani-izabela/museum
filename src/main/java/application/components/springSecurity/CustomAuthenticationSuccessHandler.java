package application.components.springSecurity;

import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private String username;
    private AppUser appUser;

    @Autowired
    private AppUserService appUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        User loginUser = (User) authentication.getPrincipal();

        username = loginUser.getUsername();
        appUser = appUserService.getAppUserByEmail(username);
        appUser.setFailedAttempt(0);
        appUserService.updateAppUser(appUser);

        response.sendRedirect("postLoginUser");
    }

    public String getUsername() {
        return username;
    }

    public AppUser getAppUser() {
        return appUser;
    }
}
