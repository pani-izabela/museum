package application.service;

import application.dto.ClientRegisterDTO;
import application.dto.EmployeeRegisterDTO;
import application.model.AppUser;
import application.model.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {
    ResponseEntity<Object> addTicket(List<Ticket> tickets);
}
