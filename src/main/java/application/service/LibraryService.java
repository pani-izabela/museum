package application.service;

import application.dto.BooksDTO;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface LibraryService {
    List<BooksDTO> getBooks();
    ResponseEntity<Object> borrowBook(String title);
    ResponseEntity<Object> checkBook(String title);
}
