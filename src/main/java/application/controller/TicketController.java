package application.controller;

import application.model.Ticket;
import application.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    private TicketService ticketService;

    @PostMapping(value = "/buyTicket", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Object> buyTicket(@RequestBody List<Ticket> tickets){
        return ticketService.addTicket(tickets);
    }
}
