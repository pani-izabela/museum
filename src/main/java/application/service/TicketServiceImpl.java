package application.service;

import application.components.springSecurity.CustomAuthenticationSuccessHandler;
import application.dao.ClientDAO;
import application.dao.MuseumFinanceDAO;
import application.model.AppUser;
import application.model.Client;
import application.model.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {

    private final MuseumFinanceDAO museumFinanceDAO;
    private final ClientDAO clientDAO;
    private final CustomAuthenticationSuccessHandler successHandler;

    public TicketServiceImpl(MuseumFinanceDAO museumFinanceDAO, ClientDAO clientDAO, CustomAuthenticationSuccessHandler successHandler) {
        this.museumFinanceDAO = museumFinanceDAO;
        this.clientDAO = clientDAO;
        this.successHandler = successHandler;
    }

    @Override
    public ResponseEntity<Object> addTicket(List<Ticket> tickets) {
        AppUser appUser = successHandler.getAppUser();
        Client client = clientDAO.getClientByAppUser(appUser);
        List<Ticket> listTicketFromDB = client.getTickets();
        listTicketFromDB.addAll(tickets);
        clientDAO.updateClientTickets(client, listTicketFromDB);
        return new ResponseEntity<>("Bilety zostały kupione", HttpStatus.OK);
    }
}
