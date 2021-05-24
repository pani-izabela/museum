package application.dao;

import application.model.Ticket;

import java.util.List;

public interface TicketDAO {
    List<Ticket> getAllTickets();
    List<Ticket> getTicketsByType(String type);
}
