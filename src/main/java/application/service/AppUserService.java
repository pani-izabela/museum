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
    List<AppUser> getAppUsers();
    AppUser getAppUser(int id);
    void deleteAppUser(int id);
}
