package application.dao;

import application.model.LibraryView;

import java.util.List;

public interface LibraryDAO {
    List<LibraryView> getBooks();
}
