package application.dao;

import application.model.Book;
import application.model.LibraryView;

import java.util.List;

public interface LibraryDAO {
    List<LibraryView> getBooks();
    Book findByTitle(String title);
}
