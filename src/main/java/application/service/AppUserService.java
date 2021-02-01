package application.service;

import application.dto.AppUserRegisterDTO;
import application.model.AppUser;
import application.model.Test;

public interface AppUserService {
    AppUser registerClient(AppUser appUser);
    Test testClient(Test test);
}
