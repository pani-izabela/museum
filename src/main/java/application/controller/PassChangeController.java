package application.controller;

import application.service.AppUserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassChangeController {

    private AppUserService appUserService;

    public PassChangeController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

}
