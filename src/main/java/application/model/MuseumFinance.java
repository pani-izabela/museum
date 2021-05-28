package application.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = MuseumFinance.GET_MUSEUM_FINANCE_BY_KEY, query = MuseumFinance.QUERY_GET_MUSEUM_FINANCE_BY_KEY)
})

@Entity
@Table(name="MUSEUM_FINANCE", uniqueConstraints = {@UniqueConstraint(columnNames = "key")})
@NoArgsConstructor
public class MuseumFinance implements Serializable {

    public static final String GET_MUSEUM_FINANCE_BY_KEY = "Client.get_museum_finance_by_key";
    public static final String QUERY_GET_MUSEUM_FINANCE_BY_KEY = "select mf from MuseumFinance mf where mf.key = :key";


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
