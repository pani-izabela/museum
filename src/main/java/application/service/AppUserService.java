package application.service;

import application.dto.AppUserRegisterDTO;
import application.dto.ClientRegisterDTO;
import application.dto.EmployeeRegisterDTO;
import application.model.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppUserService {
    ResponseEntity<Object> registerClient(ClientRegisterDTO clientRegisterDTO);
    ResponseEntity<Object> registerEmployee(EmployeeRegisterDTO employeeRegisterDTO);
    ResponseEntity<Object> changePass(String emailField, String newPassField);
    List<AppUser> getAppUsers();
    AppUser getAppUser(int id);
    AppUser getAppUserByEmail(String email);
    void deleteAppUser(int id);
    AppUser updateAppUser(AppUser appUser);
    ResponseEntity<Object> changeDataOfUser(AppUserRegisterDTO appUserRegisterDTO);


}
