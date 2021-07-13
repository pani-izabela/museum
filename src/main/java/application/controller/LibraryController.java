package application.controller;

import application.dto.BooksDTO;
import application.dto.DonationStatisticDTO;
import application.service.DonationService;
import application.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryController {

    private LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping(value = "/getBooks")
    public List<BooksDTO> getAllBooks(){
        return libraryService.getBooks();
    }

}
