package application.dao;

import application.model.Book;
import application.model.Rental;

public interface RentalDAO {

    Rental updateRental(Rental rental);
    Rental findByBook(Book book);
}
