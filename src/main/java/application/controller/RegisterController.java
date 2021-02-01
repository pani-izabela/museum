package application.controller;

import application.dto.AppUserRegisterDTO;
import application.model.AppUser;
import application.model.Test;
import application.service.AppUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private AppUserService appUserService;

    public RegisterController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping(value = "/client/addAppUser")
    public AppUser clientRegister(@RequestBody AppUser appUser){
        return appUserService.registerClient(appUser);
    }

    @PostMapping(value = "/client/test")
    public Test clientTest(@RequestBody Test test){
        return appUserService.testClient(test);
    }
}
