package application.service;

import application.components.springSecurity.CustomAuthenticationSuccessHandler;
import application.dao.ClientDAO;
import application.dao.MuseumFinanceDAO;
import application.dao.TicketDAO;
import application.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {

    private final MuseumFinanceDAO museumFinanceDAO;
    private final ClientDAO clientDAO;
    private final TicketDAO ticketDAO;
    private final CustomAuthenticationSuccessHandler successHandler;

    public TicketServiceImpl(MuseumFinanceDAO museumFinanceDAO, ClientDAO clientDAO, TicketDAO ticketDAO, CustomAuthenticationSuccessHandler successHandler) {
        this.museumFinanceDAO = museumFinanceDAO;
        this.clientDAO = clientDAO;
        this.ticketDAO = ticketDAO;
        this.successHandler = successHandler;
    }

    @Override
    public ResponseEntity<Object> addTicket(List<Ticket> tickets) {
        Client client = getClient();
        List<Ticket> listTicketFromDB = client.getTickets();
        listTicketFromDB.addAll(tickets);
        clientDAO.updateClientTickets(client, listTicketFromDB);
        return new ResponseEntity<>("Bilety zostały kupione", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> fundAccount(String amount) {
        MuseumFinance museumFinance = museumFinanceDAO.getFinanceByKey("kwota");
        float amountFromDB = (float) museumFinance.getAmount();
        amountFromDB = Float.parseFloat(amount) + amountFromDB;
        museumFinanceDAO.updateAmount(museumFinance, amountFromDB);
        return new ResponseEntity<>(museumFinance.toString(), HttpStatus.OK);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketDAO.getAllTickets();
    }

    @Override
    public boolean getClientsTickets() {
        boolean clientBoughtTicket = false;
        Client client = getClient();
        if(client!=null){
            List<Ticket> listTicketFromDB = client.getTickets();
            if(!listTicketFromDB.isEmpty()){
                clientBoughtTicket = true;
            }
        }
        else {
            clientBoughtTicket = true;
            //jesli zalogowany appUser nie figuruje jednoczesnie w tabeli CLIENT tzn, że nie jest klientem, musi
            //mieć rolę admina albo employee albo director, a oni mogą oglądać atrakcje.
            //AppUser nie może mieć roli dodatkowej do klienta
        }
        return clientBoughtTicket;
    }

    private Client getClient() {
        AppUser appUser = successHandler.getAppUser();
        return clientDAO.getClientByAppUser(appUser);
    }

}
