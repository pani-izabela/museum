package application.dao;

import application.model.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public List<AppUser> getAppUsers() {
        try{
            return em.createNamedQuery(AppUser.GET_APPUSERS, AppUser.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(int id) {
        try {
            return em.find(AppUser.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findByEmail(String email) {
        try {
            return em.createNamedQuery(AppUser.GET_APPUSER_BY_EMAIL, AppUser.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        AppUser appUser = em.find(AppUser.class, id);
        if (appUser != null)
            em.remove(appUser);
    }
}
