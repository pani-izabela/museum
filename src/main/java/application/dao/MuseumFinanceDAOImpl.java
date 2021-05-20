package application.dao;

import application.model.MuseumFinance;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class MuseumFinanceDAOImpl implements MuseumFinanceDAO{

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public MuseumFinance getFinanceByKey(String key) {
        try{
            return em.createNamedQuery(MuseumFinance.GET_MUSEUM_FINANCE_BY_KEY, MuseumFinance.class)
                    .setParameter("key", key)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public MuseumFinance addAmount(MuseumFinance museumFinance) {
        try {
            return em.merge(museumFinance);
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public MuseumFinance updateAmount(MuseumFinance museumFinance, float amount) {
        try {
            museumFinance.setAmount(amount);
            return em.merge(museumFinance);

        } catch (NoResultException e) {
            return null;
        }
    }
}
