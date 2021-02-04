package application.dao;

import application.model.AppUser;

import java.util.List;

public interface AppUserDAO {
    AppUser addAppUser(AppUser appUser);
    List<AppUser> getAppUsers();
    AppUser findById(int id);
    void deleteById(int id);
}
