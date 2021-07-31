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

    @Override
    @Transactional(readOnly = true)
    public Book findByTitle(String title) {
        try {
            return em.createNamedQuery(Book.GET_BOOK_BY_TITLE, Book.class)
                    .setParameter("title", title)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
