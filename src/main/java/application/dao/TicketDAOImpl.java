package application.dao;

import application.model.AppUser;
import application.model.Ticket;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class TicketDAOImpl implements TicketDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> getAllTickets() {
        try{
            return em.createNamedQuery(Ticket.GET_TICKETS, Ticket.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
