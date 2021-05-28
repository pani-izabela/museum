package application.service;

import application.dto.TicketStatisticDTO;
import application.model.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {
    ResponseEntity<Object> addTicket(List<Ticket> tickets);
    ResponseEntity<Object> fundAccount(String amount);
    List<Ticket> getAllTickets();
    boolean getClientsTickets();
    List<TicketStatisticDTO> getTicketStatistic();
}
