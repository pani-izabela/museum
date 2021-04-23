package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CLIENT")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Donation> donations;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_appUser")
    private AppUser appUser;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "CLIENT_TICKETS",
            joinColumns = @JoinColumn(name="id_client"),
            inverseJoinColumns = @JoinColumn(name="id_ticket"))
    private List<Ticket> tickets = new ArrayList<>();
}
