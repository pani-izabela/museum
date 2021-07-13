package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = LibraryView.GET_BOOKS, query = LibraryView.QUERY_GET_BOOKS)
})

@Entity
@Table(name = "LIBRARY_VIEW")
@Getter
@Setter
@NoArgsConstructor
public class LibraryView implements Serializable {

    public static final String GET_BOOKS = "LibraryView.get_donations";
    public static final String QUERY_GET_BOOKS = "select lv from LibraryView lv";

    @Id
    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private boolean status;

    @Column(name = "rental_time")
    private Date rental_time;

    @Column(name = "year")
    private String year;

    @Column(name = "epoch")
    private String epoch;

    @Column(name = "email")
    private String email;


}
