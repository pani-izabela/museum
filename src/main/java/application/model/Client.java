package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Client.GET_CLIENT_BY_APPUSER, query = Client.QUERY_GET_CLIENT_BY_APPUSER)
})

@Entity
@Table(name="CLIENT")
@Getter
@Setter
@NoArgsConstructor
public class Client implements Serializable {

    public static final String GET_CLIENT_BY_APPUSER = "Client.get_client_by_appuser";
    public static final String QUERY_GET_CLIENT_BY_APPUSER = "select cl from Client cl where cl.appUser = :id_appuser";

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Donation> donations;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_appuser")
    private AppUser appUser;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "CLIENT_TICKETS",
            joinColumns = @JoinColumn(name="id_client"),
            inverseJoinColumns = @JoinColumn(name="id_ticket"))
    private List<Ticket> tickets = new ArrayList<>();
}
