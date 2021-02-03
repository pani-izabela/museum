package application.service;

import application.dto.AppUserRegisterDTO;
import application.model.AppUser;
import application.model.Test;

import java.util.List;

public interface AppUserService {
    AppUser registerClient(AppUser appUser);
    Test testClient(Test test);
    List<AppUser> getAppUsers();
    List<Test> getTests();
    AppUser getAppUser(int id);
    void deleteAppUser(long id);
}
