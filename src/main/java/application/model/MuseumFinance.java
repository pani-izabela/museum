package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MUSEUM_FINANCE")
@Getter
@Setter
@NoArgsConstructor
public class MuseumFinance {

    @Id
    private int id = 1;

    private double amount;
}
