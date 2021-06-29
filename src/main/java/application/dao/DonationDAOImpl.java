package application.dao;

import application.model.Donation;
import application.model.Ticket;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DonationDAOImpl implements DonationDAO{

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Donation> getAllDonations() {
        try{
            return em.createNamedQuery(Donation.GET_DONATIONS, Donation.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Donation addDonation(Donation donation) {
        try {
            return em.merge(donation);
        } catch (NoResultException e) {
            return null;
        }
    }
}
