package application.service;

import application.dto.ClientRegisterDTO;
import application.dto.EmployeeRegisterDTO;
import application.dto.TicketStatisticDTO;
import application.model.AppUser;
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
