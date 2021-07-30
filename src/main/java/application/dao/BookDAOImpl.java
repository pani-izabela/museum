package application.dao;

import application.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class BookDAOImpl implements BookDAO{

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional
    public Book updateBook(Book book) {
        try {
            return em.merge(book);

        } catch (NoResultException e) {
            return null;
        }
    }
}
