package application.dao;

import application.model.AppUser;
import application.model.Book;
import application.model.Rental;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class RentalDAOImpl implements RentalDAO{

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;


    @Override
    @Transactional
    public Rental updateRental(Rental rental) {
        try {
            return em.merge(rental);

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Rental findByBook(Book book) {
        try {
            return em.createNamedQuery(Rental.GET_RENTAL_BY_ID_BOOK, Rental.class)
                    .setParameter("book", book)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
