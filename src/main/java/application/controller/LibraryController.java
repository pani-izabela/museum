package application.controller;

import application.dto.BooksDTO;
import application.dto.DonationStatisticDTO;
import application.dto.EmployeeRegisterDTO;
import application.model.Book;
import application.service.DonationService;
import application.service.LibraryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Api(value = "MUSEUM book API controller")
@RestController
public class LibraryController {

    private LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @ApiOperation(value = "Get all books", response = Book.class)
    @GetMapping(value = "/getBooks")
    public List<BooksDTO> getAllBooks(){
        return libraryService.getBooks();
    }

    @ApiOperation(value = "Saves the book in the client's account")
    @PostMapping(value = "/borrowBook", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Object> borrowBook(String title) throws ParseException {
        ResponseEntity<Object> resp = libraryService.borrowBook(title);
        libraryService.getBooks();
        return resp;
    }

}
