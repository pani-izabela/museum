package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = Test.GET_TESTS, query = Test.QUERY_GET_TESTS)
})

@Entity
@Table(name = "TEST")
@Setter
@Getter
@NoArgsConstructor
public class Test implements Serializable {

    public static final String GET_TESTS = "Test.get_tests";
    public static final String QUERY_GET_TESTS = "select t from Test t";

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String email;
}
