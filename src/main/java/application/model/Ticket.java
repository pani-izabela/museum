package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = Ticket.GET_TICKETS, query = Ticket.QUERY_GET_TICKETS),
        @NamedQuery(name = Ticket.GET_TICKETS_BY_TYPE, query = Ticket.QUERY_GET_TICKETS_BY_TYPE)
})

@Entity
@Table(name="TICKET")
@Getter
@Setter
@NoArgsConstructor
public class Ticket implements Serializable {

    public static final String GET_TICKETS = "Ticket.get_tickets";
    public static final String QUERY_GET_TICKETS = "select t from Ticket t";

    public static final String GET_TICKETS_BY_TYPE = "Ticket.get_ticket_by_type";
    public static final String QUERY_GET_TICKETS_BY_TYPE = "select t from Ticket t where t.type = :type";

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String type;

    @NotNull
    private double price;
}
