package application.dao;

import application.model.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class AppUserDAOImpl implements AppUserDAO{

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;


    @Override
    @Transactional
    public AppUser addAppUser(AppUser appUser) {
        try {
            return em.merge(appUser);
        } catch (NoResultException e) {
            return null;
        }
    }
}
