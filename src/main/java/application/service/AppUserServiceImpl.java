package application.service;

import application.dao.AddressDAO;
import application.dao.AppUserDAO;
import application.dao.ClientDAO;
import application.dao.EmployeeDAO;
import application.dto.ClientRegisterDTO;
import application.dto.EmployeeRegisterDTO;
import application.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("appUserService")
public class AppUserServiceImpl implements AppUserService{

    private AppUserDAO appUserDAO;
    private ClientDAO clientDAO;
    private EmployeeDAO employeeDAO;
    private AddressDAO addressDAO;

    public AppUserServiceImpl(AppUserDAO appUserDAO, ClientDAO clientDAO, EmployeeDAO employeeDAO, AddressDAO addressDAO) {
        this.appUserDAO = appUserDAO;
        this.clientDAO = clientDAO;
        this.employeeDAO = employeeDAO;
        this.addressDAO = addressDAO;
    }

    @Override
    public ResponseEntity<Object> registerClient(ClientRegisterDTO clientRegisterDTO) {
        AppUser appUser = prepareAppUserDataClient(clientRegisterDTO, new AppUser());
        appUser.setRoles(addClientRole());
        addClient(appUser);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> registerEmployee(EmployeeRegisterDTO employeeRegisterDTO) {
        AppUser appUser = prepareAppUserDataEmployee(employeeRegisterDTO, new AppUser());
        appUser.setRoles(addEmployeeRole());
        addEmployee(employeeRegisterDTO, appUser);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
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
    private AppUser prepareAppUserDataClient(ClientRegisterDTO clientRegisterDTO, AppUser appUser) {
        appUser.setEmail(clientRegisterDTO.getEmail());
        appUser.setPassword(clientRegisterDTO.getPassword());
        appUser.setName(clientRegisterDTO.getFirstName());
        appUser.setSurname(clientRegisterDTO.getLastName());
        return appUser;
    }
    private AppUser prepareAppUserDataEmployee(EmployeeRegisterDTO employeeRegisterDTO, AppUser appUser) {
        appUser.setEmail(employeeRegisterDTO.getEmail());
        appUser.setPassword(employeeRegisterDTO.getPassword());
        appUser.setName(employeeRegisterDTO.getFirstName());
        appUser.setSurname(employeeRegisterDTO.getLastName());
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
        //addressDAO.addAddress(address);
        return address;
    }
}
