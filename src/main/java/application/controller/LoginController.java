package application.controller;

import application.service.AppUserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private AppUserService appUserService;

    public LoginController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

}
