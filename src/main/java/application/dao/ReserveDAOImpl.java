package application.dao;

import application.model.MuseumFinance;
import application.model.Reserve;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class ReserveDAOImpl implements ReserveDAO{

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Reserve getReserveByKey(String key) {
        try{
            return em.createNamedQuery(Reserve.GET_RESERVE_BY_KEY, Reserve.class)
                    .setParameter("key", key)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public Reserve updateReserve(Reserve reserve, float amount) {
        try {
            reserve.setAmount(amount);
            return em.merge(reserve);

        } catch (NoResultException e) {
            return null;
        }
    }
}
