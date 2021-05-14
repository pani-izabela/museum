package application.service;

import application.dao.MuseumFinanceDAO;
import application.dao.TicketDAO;
import org.springframework.stereotype.Service;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {

    private final TicketDAO ticketDAO;
    private final MuseumFinanceDAO museumFinanceDAO;

    public TicketServiceImpl(TicketDAO ticketDAO, MuseumFinanceDAO museumFinanceDAO) {
        this.ticketDAO = ticketDAO;
        this.museumFinanceDAO = museumFinanceDAO;
    }
}
