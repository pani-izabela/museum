package application.controller;

import application.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    private TicketService ticketService;

    /*@PostMapping(value = "/buyTicket", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Object> buyTicket(String ticketType, double ticketPrice){
        return ticketService.buyTicket(ticketType, ticketPrice);
    }*/
}
