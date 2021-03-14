package application.dao;

import application.model.AppUser;

import java.util.List;

public interface AppUserDAO {
    AppUser addAppUser(AppUser appUser);
    AppUser updatePass(AppUser appUser, String newPass);
    List<AppUser> getAppUsers();
    AppUser findById(int id);
    AppUser findByEmail(String email);
    void deleteById(int id);
    AppUser updateFailedAttempts(AppUser appUser, int failedAttempt);
    AppUser updateAppUser(AppUser appUser);
}
