package application.service;

import application.dto.ClientRegisterDTO;
import application.dto.EmployeeRegisterDTO;
import application.model.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppUserService {
//    AppUser register(AppUserRegisterDTO appUserDTO);
    ResponseEntity<Object> registerClient(ClientRegisterDTO clientRegisterDTO);
    ResponseEntity<Object> registerEmployee(EmployeeRegisterDTO employeeRegisterDTO);
    ResponseEntity<Object> changePass(String emailField, String newPassField);
    List<AppUser> getAppUsers();
    AppUser getAppUser(int id);
    AppUser getAppUserByEmail(String email);
    void deleteAppUser(int id);
}
