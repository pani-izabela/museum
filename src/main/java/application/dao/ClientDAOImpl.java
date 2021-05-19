package application.dao;

import application.model.AppUser;
import application.model.Client;
import application.model.Ticket;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;


    @Override
    @Transactional
    public Client addClient(Client client) {
        try {
            return em.merge(client);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Client getClientByAppUser(AppUser appUser) {
        try{
            return em.createNamedQuery(Client.GET_CLIENT_BY_APPUSER, Client.class)
                    .setParameter("id_appuser", appUser)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Client updateClientTickets(Client client, List<Ticket> ticketList) {
        try {
            client.setTickets(ticketList);
            return em.merge(client);

        } catch (NoResultException e) {
            return null;
        }
    }


}
