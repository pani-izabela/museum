package application.controller;

import application.model.AppUser;
import application.model.Role;
import application.model.Test;
import application.service.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/getAppUsers")
    public List<AppUser> getAppUsers(){
        return appUserService.getAppUsers();
    }

    @GetMapping(value = "/getAppUser")
    public AppUser getAppUser(@RequestBody @RequestParam int id){
        return appUserService.getAppUser(id);
    }

    @DeleteMapping(value = "/deleteAppUser/{id}")
    public void deleteAppUser(@PathVariable long id){
        appUserService.deleteAppUser(id);
    }

    @GetMapping(value = "/getTests")
    public List<Test> getTests(){
        return appUserService.getTests();
    }
}
