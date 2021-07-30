package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Rental.GET_RENTAL_BY_ID_BOOK, query = Rental.QUERY_GET_RENTAL_BY_ID_BOOK)
})

@Entity
@Table(name = "RENTAL")
@Getter
@Setter
@NoArgsConstructor
public class Rental implements Serializable {

    public static final String GET_RENTAL_BY_ID_BOOK = "Rental.get_rental_by_id_book";
    public static final String QUERY_GET_RENTAL_BY_ID_BOOK = "select r from Rental r where r.book = :book";

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date rental_time;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_book")
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_client")
    private Client client;
}
