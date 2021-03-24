package application.dao;

import application.model.AppUser;

import java.util.List;

public interface AppUserDAO {

    AppUser updatePass(AppUser appUser, String newPass);
    List<AppUser> getAppUsers();
    AppUser findById(int id);
    AppUser findByEmail(String email);
    void deleteById(int id);
    AppUser updateAppUser(AppUser appUser);
}
