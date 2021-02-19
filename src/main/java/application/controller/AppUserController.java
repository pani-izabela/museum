package application.controller;

import application.dto.ClientRegisterDTO;
import application.dto.EmployeeRegisterDTO;
import application.model.AppUser;
import application.model.Role;
import application.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppUserController {

    private AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping(value = "/client/addAppUser", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Object> clientRegister(@RequestBody ClientRegisterDTO clientRegisterDTO){
        return appUserService.registerClient(clientRegisterDTO);
    }

    @PostMapping(value = "/employee/addAppUser", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Object> employeeRegister(@RequestBody EmployeeRegisterDTO employeeRegisterDTO){
        return appUserService.registerEmployee(employeeRegisterDTO);
    }

    @PostMapping(value = "/changePassword", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Object> changePass(String email, String newPass){
        return appUserService.changePass(email, newPass);
    }

    @GetMapping(value = "/getRoles")
    public List<Role> getRoles(@RequestBody @RequestParam String email){
        return appUserService.getAppUserByEmail(email).getRoles();
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
