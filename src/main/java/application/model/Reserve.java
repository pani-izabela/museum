package application.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name=Reserve.GET_RESERVE_BY_KEY, query=Reserve.QUERY_GET_RESERVE_BY_KEY)
})

@Entity
@Table(name="RESERVE", uniqueConstraints = {@UniqueConstraint(columnNames = "key")})
@NoArgsConstructor
public class Reserve implements Serializable {

    public static final String GET_RESERVE_BY_KEY = "Reserve.get_reserve_by_key";
    public static final String QUERY_GET_RESERVE_BY_KEY = "select r from Reserve r where r.key = :key";

    private String key;
    private double amount;

    @Id
    @Column(name = "key", unique = true, nullable = false)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
