package application.service;

import application.dao.AppUserDAO;
import application.dao.TestDAO;
import application.dto.AppUserRegisterDTO;
import application.model.AppUser;
import application.model.Role;
import application.model.Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("appUserService")
public class AppUserServiceImpl implements AppUserService{

    private AppUserDAO appUserDAO;
    private TestDAO testDAO;

    public AppUserServiceImpl(AppUserDAO appUserDAO, TestDAO testDAO) {
        this.appUserDAO = appUserDAO;
        this.testDAO = testDAO;
    }

    @Override
    public AppUser registerClient(AppUser appUser) {
        //AppUser appUser = prepareAppUserData(appUserRegisterDTO, new AppUser());
        List<Role> list = new ArrayList();
        Role roleClient = new Role();
        roleClient.setId(4);
        roleClient.setName("client");
        list.add(roleClient);
        appUser.setRoles(list);
        return appUserDAO.addAppUser(appUser);
    }

    @Override
    public Test testClient(Test test) {
        return testDAO.addTest(test);
    }

    @Override
    public List<AppUser> getAppUsers() {
        return appUserDAO.getAppUsers();
    }

    @Override
    public List<Test> getTests() {
        return appUserDAO.getTests();
    }


    @Override
    public AppUser getAppUser(int id) {
        return appUserDAO.findById(id);
    }

    @Override
    public void deleteAppUser(long id) {
        appUserDAO.deleteById(id);
    }

    //--------- metody prywatne ----------
    private AppUser prepareAppUserData(AppUserRegisterDTO appUserRegisterDTO, AppUser appUser) {
        appUser.setEmail(appUserRegisterDTO.getEmail());
        appUser.setPassword(appUserRegisterDTO.getEmail());
        appUser.setName(appUserRegisterDTO.getFirstName());
        appUser.setSurname(appUserRegisterDTO.getLastName());
        return appUser;
    }
}
