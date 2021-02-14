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
        @NamedQuery(name = AppUser.GET_APPUSERS, query = AppUser.QUERY_GET_APPUSERS),
        @NamedQuery(name = AppUser.GET_APPUSER_BY_ID, query = AppUser.QUERY_GET_APPUSER_BY_ID),
        @NamedQuery(name = AppUser.GET_APPUSER_BY_EMAIL, query = AppUser.QUERY_GET_APPUSER_BY_EMAIL)
})

@Entity
@Table(name = "APPUSER")
@Setter
@Getter
@NoArgsConstructor
public class AppUser implements Serializable {

    public static final String GET_APPUSERS = "AppUser.get_appusers";
    public static final String QUERY_GET_APPUSERS = "select au from AppUser au";

    public static final String GET_APPUSER_BY_ID = "AppUser.get_appuser_by_id";
    public static final String QUERY_GET_APPUSER_BY_ID = "select au from AppUser au where au.id = :id";

    public static final String GET_APPUSER_BY_EMAIL = "AppUser.get_appuser_by_email";
    public static final String QUERY_GET_APPUSER_BY_EMAIL = "select au from AppUser au where au.email = :email";

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name="id_appUser"),
            inverseJoinColumns = @JoinColumn(name="id_role"))
    private List<Role> roles = new ArrayList<>();
}
