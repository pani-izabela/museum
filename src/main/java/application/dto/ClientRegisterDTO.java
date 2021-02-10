package application.dto;

import lombok.Data;

@Data
public class ClientRegisterDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
