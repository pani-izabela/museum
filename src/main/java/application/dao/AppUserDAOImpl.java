package application.dao;

import application.model.AppUser;
import application.model.Test;
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
            System.out.println("przed merge obiekt z id: " + appUser.getId());
            Object object = em.merge(appUser);
            return (AppUser) object;
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
    public List<Test> getTests() {
        try{
            return em.createNamedQuery(Test.GET_TESTS, Test.class)
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
    @Transactional
    public void deleteById(long id) {
        AppUser appUser = em.find(AppUser.class, id);
        if (appUser != null)
            em.remove(appUser);
    }
}
