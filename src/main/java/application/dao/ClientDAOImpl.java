package application.dao;

import application.model.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

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
}
