package application.controller;

import application.dto.BooksDTO;
import application.dto.DonationStatisticDTO;
import application.model.Book;
import application.service.DonationService;
import application.service.LibraryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
