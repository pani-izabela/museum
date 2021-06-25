package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = Donation.GET_DONATIONS, query = Donation.QUERY_GET_DONATIONS)
})

@Entity
@Table(name="DONATION")
@Setter
@Getter
@NoArgsConstructor
public class Donation implements Serializable {

    public static final String GET_DONATIONS = "Donation.get_donations";
    public static final String QUERY_GET_DONATIONS = "select d from Donation d";

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private double amount;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_client")
    private Client client;


}
