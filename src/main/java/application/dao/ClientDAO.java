package application.dao;

import application.model.AppUser;
import application.model.Client;
import application.model.Ticket;

import java.util.List;

public interface ClientDAO {
    Client addClient(Client client);
    Client getClientByAppUser(AppUser appUser);
    Client updateClientTickets(Client client, List<Ticket> ticketList);
}
