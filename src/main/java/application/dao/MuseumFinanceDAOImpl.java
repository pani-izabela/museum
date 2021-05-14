package application.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class MuseumFinanceDAOImpl implements MuseumFinanceDAO{

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

}
