package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = Book.GET_BOOK_BY_TITLE, query = Book.QUERY_GET_BOOK_BY_TITLE)
})

@Entity
@Table(name = "BOOK")
@Getter
@Setter
@NoArgsConstructor
public class Book implements Serializable {

    public static final String GET_BOOK_BY_TITLE = "Book.get_book_by_title";
    public static final String QUERY_GET_BOOK_BY_TITLE = "select b from Book b where b.title = :title";

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String title;
    @NotNull
    private boolean status;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_historydata")
    private HistoryData historyData;
}
