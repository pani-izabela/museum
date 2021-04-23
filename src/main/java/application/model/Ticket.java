package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="TICKET")
@Getter
@Setter
@NoArgsConstructor
public class Ticket implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String type;

    @NotNull
    private double price;
}
