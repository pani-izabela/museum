package application.service;

import application.dao.AppUserDAO;
import application.dto.AppUserRegisterDTO;
import application.model.AppUser;
import application.model.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("appUserService")
public class AppUserServiceImpl implements AppUserService{

    private AppUserDAO appUserDAO;

    public AppUserServiceImpl(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @Override
    public AppUser registerClient(AppUserRegisterDTO appUserRegisterDTO) {
        AppUser appUser = prepareAppUserData(appUserRegisterDTO, new AppUser());
        appUser.setRoles(addClientRole());
        return appUserDAO.addAppUser(appUser);
    }

    @Override
    public List<AppUser> getAppUsers() {
        return appUserDAO.getAppUsers();
    }

    @Override
    public AppUser getAppUser(int id) {
        return appUserDAO.findById(id);
    }

    @Override
    public void deleteAppUser(int id) {
        appUserDAO.deleteById(id);
    }

    //--------- metody prywatne ----------
    private AppUser prepareAppUserData(AppUserRegisterDTO appUserRegisterDTO, AppUser appUser) {
        appUser.setEmail(appUserRegisterDTO.getEmail());
        appUser.setPassword(appUserRegisterDTO.getPassword());
        appUser.setName(appUserRegisterDTO.getFirstName());
        appUser.setSurname(appUserRegisterDTO.getLastName());
        return appUser;
    }

    private List<Role> addClientRole(){
        List<Role> list = new ArrayList();
        Role roleClient = new Role();
        roleClient.setId(4);
        roleClient.setName("client");
        list.add(roleClient);
        return list;
    }
}
