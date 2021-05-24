package application.controller;

import application.dto.TicketStatisticDTO;
import application.model.Ticket;
import application.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping(value = "/fundAccount")
    public ResponseEntity<Object> fundAccount(String amount){
        return ticketService.fundAccount(amount);
    }

    /*@GetMapping(value = "/getTickets")
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }*/
    @GetMapping(value = "/getTickets")
    public List<TicketStatisticDTO> getAllTickets(){
        return ticketService.getTicketStatistic();
    }


    @GetMapping(value = "/checkClientsTicket")
    public boolean checkTicket(){
        return ticketService.getClientsTickets();
    }
}
