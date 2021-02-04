package application.controller;

import application.dto.AppUserRegisterDTO;
import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegisterController {

    private AppUserService appUserService;

    public RegisterController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /*@PostMapping(value = "/client/addAppUser")
    public AppUser clientRegister(@RequestBody AppUser appUser){
        return appUserService.registerClient(appUser);
    }*/

    @PostMapping(value = "/client/addAppUser")
    public AppUser clientRegister(@RequestBody AppUserRegisterDTO appUserRegisterDTO){
        return appUserService.registerClient(appUserRegisterDTO);
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
    public void deleteAppUser(@PathVariable int id){
        appUserService.deleteAppUser(id);
    }
}
