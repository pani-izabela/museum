package application.service;

import application.dto.AppUserRegisterDTO;
import application.model.AppUser;

import java.util.List;

public interface AppUserService {
    AppUser registerClient(AppUserRegisterDTO appUserDTO);
    List<AppUser> getAppUsers();
    AppUser getAppUser(int id);
    void deleteAppUser(int id);
}
