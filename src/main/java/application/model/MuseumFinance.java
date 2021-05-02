package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="MUSEUM_FINANCE", uniqueConstraints = {@UniqueConstraint(columnNames = "key")})
@NoArgsConstructor
public class MuseumFinance {

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
