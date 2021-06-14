package application.service;

import application.components.springSecurity.CustomAuthenticationSuccessHandler;
import application.components.springSecurity.MyUserDetails;
import application.dao.AppUserDAO;
import application.dao.ClientDAO;
import application.dao.EmployeeDAO;
import application.dto.AppUserRegisterDTO;
import application.dto.ClientRegisterDTO;
import application.dto.EmployeeRegisterDTO;
import application.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("appUserService")
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    private final AppUserDAO appUserDAO;
    private final ClientDAO clientDAO;
    private final EmployeeDAO employeeDAO;
    //private Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/messages.properties"));
    @Resource(name = "myProps")
    private final Properties properties;


    public AppUserServiceImpl(AppUserDAO appUserDAO, ClientDAO clientDAO, EmployeeDAO employeeDAO) throws IOException {
        this.appUserDAO = appUserDAO;
        this.clientDAO = clientDAO;
        this.employeeDAO = employeeDAO;
        properties = new Properties();
    }


    @Override
    public ResponseEntity<Object> registerClient(ClientRegisterDTO clientRegisterDTO) {
        if(checkAppUserDuringRegistration(clientRegisterDTO.getEmail(), clientRegisterDTO.getPassword(), clientRegisterDTO.getLastName()) == null){
            AppUser appUser = prepareAppUserData(clientRegisterDTO, new AppUser());
            appUser.setRoles(addClientRole());
            addClient(appUser);
            return new ResponseEntity<>(appUser.toString(), HttpStatus.CREATED);
        }
        else{
            return checkAppUserDuringRegistration(clientRegisterDTO.getEmail(), clientRegisterDTO.getPassword(), clientRegisterDTO.getLastName());
        }
    }

    @Override
    public ResponseEntity<Object> registerEmployee(EmployeeRegisterDTO employeeRegisterDTO) {
        if(checkAppUserDuringRegistration(employeeRegisterDTO.getEmail(), employeeRegisterDTO.getPassword(), employeeRegisterDTO.getLastName())==null){
            AppUser appUser = prepareAppUserData(employeeRegisterDTO, new AppUser());
            appUser.setRoles(addEmployeeRole());
            addEmployee(employeeRegisterDTO, appUser);
            return new ResponseEntity<>(appUser.toString(), HttpStatus.CREATED);
        }
        else {
            return checkAppUserDuringRegistration(employeeRegisterDTO.getEmail(), employeeRegisterDTO.getPassword(), employeeRegisterDTO.getLastName());
        }
    }

    @Override
    public ResponseEntity<Object> changePass(String emailField, String newPassField) {
        AppUser appUserFromDb = findAppUserByEmail(emailField, newPassField);
        if(appUserFromDb==null){
            return new ResponseEntity<>(properties.getProperty("service.appUserServiceImpl.ERROR_CHANGE_PASS"), HttpStatus.BAD_REQUEST);
        }
        else{
            if(!passwordValid(newPassField, appUserFromDb.getSurname())){
                return new ResponseEntity<>(properties.getProperty("service.appUserServiceImpl.WRONG_PASS"),HttpStatus.BAD_REQUEST);
            }
            appUserDAO.updatePass(appUserFromDb, newPassField);
            return new ResponseEntity<>(properties.getProperty("service.appUserServiceImpl.CHANGE_PASS"), HttpStatus.OK);
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

    @Override
    public ResponseEntity<Object> changeDataOfUser(AppUserRegisterDTO appUserRegisterDTO) {
        AppUser appUserFromDB = getLoggedAppUser();
        if(appUserFromDB==null){
            return new ResponseEntity<>(properties.getProperty("service.appUserServiceImpl.ERROR_CHANGE_DATA"), HttpStatus.BAD_REQUEST);
        }
        else{
            updateDataOfAppUser(appUserRegisterDTO, appUserFromDB);
        }
        String textToResponse = ((appUserFromDB.getName() + " " + appUserFromDB.getSurname() + " " + properties.getProperty("service.appUserServiceImpl.CHANGE_DATA")));
        return new ResponseEntity<>(textToResponse, HttpStatus.OK);
    }

    //-------------spring security

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserDAO.findByEmail(username);
        if(appUser == null){
            throw new UsernameNotFoundException("Nie mogę znaleźć takiego usera");
        }
        return new MyUserDetails(appUser);
    }

    //--------- metody prywatne ----------
    private ResponseEntity<Object> checkAppUserDuringRegistration(String email, String pass, String lastName){
        if(appUserDAO.findByEmail(email)!=null){
            return new ResponseEntity<>(properties.getProperty("service.appUserServiceImpl.NOT_UNIQUE_MAIL"),HttpStatus.BAD_REQUEST);
        }
        if(!passwordValid(pass, lastName)){
            return new ResponseEntity<>(properties.getProperty("service.appUserServiceImpl.WRONG_PASS"),HttpStatus.BAD_REQUEST);
        }
        else {
            return null;
        }
    }

    private AppUser prepareAppUserData(AppUserRegisterDTO appUserRegisterDTO, AppUser appUser){
        appUser.setEmail(appUserRegisterDTO.getEmail());
        appUser.setPassword(appUserRegisterDTO.getPassword());
        appUser.setName(appUserRegisterDTO.getFirstName());
        appUser.setSurname(appUserRegisterDTO.getLastName());
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
        List<Role> list = new ArrayList<Role>();
        Role roleEmployee = new Role();
        roleEmployee.setId(3);
        roleEmployee.setName("employee");
        list.add(roleEmployee);
        return list;
    }

    private void addClient(AppUser appUser){
        Client client = new Client();
        client.setAppUser(appUser);
        clientDAO.addClient(client);
    }

    private void addEmployee(EmployeeRegisterDTO employeeRegisterDTO, AppUser appUser){
        Employee employee = new Employee();
        employee.setPosition(employeeRegisterDTO.getPosition());
        employee.setAccountNumber(employeeRegisterDTO.getAccountNumber());
        employee.setPhone("");
        employee.setAddress(prepareAddress(employeeRegisterDTO, new Address()));
        employee.setAppUser(appUser);
        employeeDAO.addEmployee(employee);
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
        if (appUserFromDB != null && appUserFromDB.getEmail().contains(email) && (!newPass.equals(appUserFromDB.getPassword())) && (!newPass.isEmpty())){
            return appUserFromDB;
        }
        else {
            return null;
        }
    }

    private void updateDataOfAppUser(AppUserRegisterDTO appUserRegisterDTO, AppUser appUserFromDB) {
        if(!appUserFromDB.getEmail().equals(appUserRegisterDTO.getEmail())){
            appUserFromDB.setEmail(appUserRegisterDTO.getEmail());
        }
        if(!appUserFromDB.getName().equals(appUserRegisterDTO.getFirstName())){
            appUserFromDB.setName(appUserRegisterDTO.getFirstName());
        }
        if(!appUserFromDB.getSurname().equals(appUserRegisterDTO.getLastName())){
            appUserFromDB.setSurname(appUserRegisterDTO.getLastName());
        }
        if(!appUserFromDB.getPassword().equals(appUserRegisterDTO.getPassword())){
            appUserFromDB.setPassword(appUserRegisterDTO.getPassword());
        }
        updateAppUser(appUserFromDB);
    }

    private AppUser getLoggedAppUser(){
        MyUserDetails myUserDetails = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return appUserDAO.findByEmail(myUserDetails.getUsername());
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
