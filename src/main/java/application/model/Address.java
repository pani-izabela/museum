package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String street;

    @NotNull
    private String localNumber;

    @NotNull
    private String homeNumber;

    @NotNull
    private String city;

    @NotNull
    private String postcode;

}
