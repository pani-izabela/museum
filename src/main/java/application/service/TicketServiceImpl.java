package application.service;

import application.components.springSecurity.CustomAuthenticationSuccessHandler;
import application.dao.ClientDAO;
import application.dao.MuseumFinanceDAO;
import application.dao.TicketDAO;
import application.dto.TicketStatisticDTO;
import application.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {

    private final MuseumFinanceDAO museumFinanceDAO;
    private final ClientDAO clientDAO;
    private final TicketDAO ticketDAO;
    private final CustomAuthenticationSuccessHandler successHandler;
    @Resource(name = "myProps")
    private final Properties properties;

    public TicketServiceImpl(MuseumFinanceDAO museumFinanceDAO, ClientDAO clientDAO, TicketDAO ticketDAO, CustomAuthenticationSuccessHandler successHandler) {
        this.museumFinanceDAO = museumFinanceDAO;
        this.clientDAO = clientDAO;
        this.ticketDAO = ticketDAO;
        this.successHandler = successHandler;
        properties = new Properties();
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

    @Override
    public List<TicketStatisticDTO> getTicketStatistic() {
        List<TicketStatisticDTO> listTicketStatistic = new ArrayList<>();
        List<Ticket> ticketList = ticketDAO.getAllTickets();
        for(int i=1; i<5; i++) {
            addPositionToList(listTicketStatistic, ticketList, i);
        }
        return listTicketStatistic;
    }

    private void addPositionToList(List<TicketStatisticDTO> listTicketStatistic, List<Ticket> ticketList, int type) {
        List<Ticket> normalTicketList = getTicketsListByType(ticketList, properties.getProperty("pages.tickets.ticketType"+type));
        listTicketStatistic.add(getTicketStatisticDTO(normalTicketList, properties.getProperty("pages.tickets.ticketType"+type)));
    }

    private List<Ticket> getTicketsListByType(List<Ticket> ticketList, String type) {
        return ticketList.stream()
                    .filter(t->t.getType().equals(type))
                    .collect(Collectors.toList());
    }

    private TicketStatisticDTO getTicketStatisticDTO(List<Ticket> normalTicketList, String type) {
        TicketStatisticDTO ticketStatisticDTO = new TicketStatisticDTO();
        ticketStatisticDTO.setType(type);
        ticketStatisticDTO.setQuantity(normalTicketList.size());
        ticketStatisticDTO.setAmount(normalTicketList.stream().mapToDouble(Ticket::getPrice).sum());
        return ticketStatisticDTO;
    }

    private Client getClient() {
        AppUser appUser = successHandler.getAppUser();
        return clientDAO.getClientByAppUser(appUser);
    }

}
