package application.dao;

import application.model.Book;

public interface BookDAO {
    Book updateBook(Book book);
    Book findByTitle(String title);
}
