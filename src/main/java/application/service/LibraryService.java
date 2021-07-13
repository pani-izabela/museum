package application.service;

import application.dto.BooksDTO;

import java.util.List;

public interface LibraryService {
    List<BooksDTO> getBooks();
}
