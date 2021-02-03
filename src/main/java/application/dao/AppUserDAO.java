package application.dao;

import application.model.AppUser;
import application.model.Test;

import java.util.List;

public interface AppUserDAO {
    AppUser addAppUser(AppUser appUser);
    List<AppUser> getAppUsers();
    List<Test> getTests();
    AppUser findById(int id);
    void deleteById(long id);
}
