package application.service;

import application.components.Enum;
import application.dao.AppUserDAO;
import application.dao.ClientDAO;
import application.dao.EmployeeDAO;
import application.dto.ClientRegisterDTO;
import application.dto.EmployeeRegisterDTO;
import application.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("appUserService")
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    private AppUserDAO appUserDAO;
    private ClientDAO clientDAO;
    private EmployeeDAO employeeDAO;

    public AppUserServiceImpl(AppUserDAO appUserDAO, ClientDAO clientDAO, EmployeeDAO employeeDAO) {
        this.appUserDAO = appUserDAO;
        this.clientDAO = clientDAO;
        this.employeeDAO = employeeDAO;
    }

    public AppUserServiceImpl() {

    }

    void setAppUserDAO(AppUserDAO appUserdao){
        appUserDAO = appUserdao;
    }

    @Override
    public ResponseEntity<Object> registerClient(ClientRegisterDTO clientRegisterDTO) {
        if(appUserDAO.findByEmail(clientRegisterDTO.getEmail())!=null){
            return new ResponseEntity<>(Enum.NOT_UNIQUE_MAIL,HttpStatus.BAD_REQUEST);
        }
        if(passwordValid(clientRegisterDTO.getPassword(), clientRegisterDTO.getLastName())==false){
            return new ResponseEntity<>(Enum.WRONG_PASS,HttpStatus.BAD_REQUEST);
        }
        AppUser appUser = prepareAppUserDataClient(clientRegisterDTO, new AppUser());
        appUser.setRoles(addClientRole());
        addClient(appUser);
        return new ResponseEntity<>(appUser.toString(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> registerEmployee(EmployeeRegisterDTO employeeRegisterDTO) {
        if(appUserDAO.findByEmail(employeeRegisterDTO.getEmail())!=null){
            return new ResponseEntity<>(Enum.NOT_UNIQUE_MAIL,HttpStatus.BAD_REQUEST);
        }
        if(passwordValid(employeeRegisterDTO.getPassword(), employeeRegisterDTO.getLastName())==false){
            return new ResponseEntity<>(Enum.WRONG_PASS,HttpStatus.BAD_REQUEST);
        }
        AppUser appUser = prepareAppUserDataEmployee(employeeRegisterDTO, new AppUser());
        appUser.setRoles(addEmployeeRole());
        addEmployee(employeeRegisterDTO, appUser);
        return new ResponseEntity<>(appUser.toString(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> changePass(String emailField, String newPassField) {
        AppUser appUserFromDb = findAppUserByEmail(emailField, newPassField);
        if(appUserFromDb==null){
            return new ResponseEntity<>(Enum.ERROR_CHANGE_PASS, HttpStatus.BAD_REQUEST);
        }
        else{
            if(!passwordValid(newPassField, appUserFromDb.getSurname())){
                return new ResponseEntity<>(Enum.WRONG_PASS,HttpStatus.BAD_REQUEST);
            }
            appUserDAO.updatePass(appUserFromDb, newPassField);
            return new ResponseEntity<>(Enum.CHANGE_PASS, HttpStatus.OK);
        }
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
    public AppUser getAppUserByEmail(String email) {
        return appUserDAO.findByEmail(email);
    }

    @Override
    public void deleteAppUser(int id) {
        appUserDAO.deleteById(id);
    }

    @Override
    public AppUser updateAppUser(AppUser appUser) {
        return appUserDAO.updateAppUser(appUser);
    }

    //-------------spring security

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserDAO.findByEmail(username);
        if(appUser == null){
            throw new UsernameNotFoundException("Nie mogę znaleźć takiego usera");
        }
        User logedUser = new User(username, appUser.getPassword(), appUser.getEnabled(),
                true, true, appUser.getAccountNonLocked(), getAuthorities(appUser));
        return logedUser;
    }

    //--------- metody prywatne ----------
    private AppUser prepareAppUserDataClient(ClientRegisterDTO clientRegisterDTO, AppUser appUser) {
        appUser.setEmail(clientRegisterDTO.getEmail());
        appUser.setPassword(clientRegisterDTO.getPassword());
        appUser.setName(clientRegisterDTO.getFirstName());
        appUser.setSurname(clientRegisterDTO.getLastName());
        appUser.setAccountNonLocked(true);
        appUser.setEnabled(true);
        return appUser;
    }
    private AppUser prepareAppUserDataEmployee(EmployeeRegisterDTO employeeRegisterDTO, AppUser appUser) {
        appUser.setEmail(employeeRegisterDTO.getEmail());
        appUser.setPassword(employeeRegisterDTO.getPassword());
        appUser.setName(employeeRegisterDTO.getFirstName());
        appUser.setSurname(employeeRegisterDTO.getLastName());
        appUser.setAccountNonLocked(true);
        appUser.setEnabled(true);
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

    private List<Role> addEmployeeRole(){
        List<Role> list = new ArrayList();
        Role roleEmployee = new Role();
        roleEmployee.setId(3);
        roleEmployee.setName("employee");
        list.add(roleEmployee);
        return list;
    }

    private Client addClient(AppUser appUser){
        Client client = new Client();
        client.setAppUser(appUser);
        clientDAO.addClient(client);
        return client;
    }

    private Employee addEmployee(EmployeeRegisterDTO employeeRegisterDTO, AppUser appUser){
        Employee employee = new Employee();
        employee.setPosition(employeeRegisterDTO.getPosition());
        employee.setAccountNumber(employeeRegisterDTO.getAccountNumber());
        employee.setPhone("");
        employee.setAddress(prepareAddress(employeeRegisterDTO, new Address()));
        employee.setAppUser(appUser);
        employeeDAO.addEmployee(employee);
        return employee;
    }

    private Address prepareAddress(EmployeeRegisterDTO employeeRegisterDTO, Address address){
        address.setStreet(employeeRegisterDTO.getStreet());
        address.setHomeNumber(employeeRegisterDTO.getHomeNumber());
        address.setLocalNumber(employeeRegisterDTO.getLocalNumber());
        address.setCity(employeeRegisterDTO.getCity());
        address.setPostcode(employeeRegisterDTO.getPostcode());
        return address;
    }

    private boolean passwordValid(String pass, String lastName){
        String regex = "^((.*?[A-Z]){2})(.*?[a-z])(.*?[#?!@$%^&*-])((?i)(?!" + lastName + ").)*$";
        Pattern p = Pattern.compile(regex);
        if (pass == null) {
            return false;
        }
        Matcher m = p.matcher(pass);
        return m.matches();
    }

    private AppUser findAppUserByEmail(String email, String newPass) {
        AppUser appUserFromDB = appUserDAO.findByEmail(email);
        if (appUserFromDB.getEmail().contains(email) && (!newPass.equals(appUserFromDB.getPassword())) && (!newPass.isEmpty())){
            return appUserFromDB;
        }
        else {
            return null;
        }
    }

//    ------Spring security
    private Collection<? extends GrantedAuthority> getAuthorities(AppUser appUser) {
        List<Role> roles = appUser.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

}
