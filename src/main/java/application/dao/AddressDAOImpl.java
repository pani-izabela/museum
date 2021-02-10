package application.dao;

import application.model.Address;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class AddressDAOImpl implements AddressDAO{

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional
    public Address addAddress(Address address) {
        try {
            return em.merge(address);
        } catch (NoResultException e) {
            return null;
        }
    }
}
