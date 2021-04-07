package application.dto;

import lombok.Data;

@Data
public class AppUserRegisterDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
