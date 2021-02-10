package application.controller;

import application.dto.ClientRegisterDTO;
import application.dto.EmployeeRegisterDTO;
import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegisterController {

    private AppUserService appUserService;

    public RegisterController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping(value = "/client/addAppUser")
    public ResponseEntity<Object> clientRegister(@RequestBody ClientRegisterDTO clientRegisterDTO){
        return appUserService.registerClient(clientRegisterDTO);
    }

    @PostMapping(value = "/employee/addAppUser")
    public ResponseEntity<Object> employeeRegister(@RequestBody EmployeeRegisterDTO employeeRegisterDTO){
        return appUserService.registerEmployee(employeeRegisterDTO);
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
