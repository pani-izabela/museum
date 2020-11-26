package application.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "APPUSER")
@Data
public class AppUser {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
}
