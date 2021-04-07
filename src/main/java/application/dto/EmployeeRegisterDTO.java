package application.dto;

import lombok.Data;

@Data
public class EmployeeRegisterDTO extends  AppUserRegisterDTO{
    /*private String email;
    private String firstName;
    private String lastName;
    private String password;*/

    private String position;
    private String accountNumber;

    private String street;
    private String homeNumber;
    private String localNumber;
    private String city;
    private String postcode;
}
