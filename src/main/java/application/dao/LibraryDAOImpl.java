package application.dao;

import application.model.AppUser;
import application.model.Book;
import application.model.Donation;
import application.model.LibraryView;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class LibraryDAOImpl implements LibraryDAO{

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<LibraryView> getBooks() {
        try{
            return em.createNamedQuery(LibraryView.GET_BOOKS, LibraryView.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

   /* @Override
    @Transactional(readOnly = true)
    public Book findByTitle(String title) {
        try {
            return em.createNamedQuery(Book.GET_BOOK_BY_TITLE, Book.class)
                    .setParameter("title", title)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }*/
}
