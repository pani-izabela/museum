package application.service;

import application.components.springSecurity.MyUserDetails;
import application.dao.*;
import application.dto.BooksDTO;
import application.model.Book;
import application.model.Client;
import application.model.LibraryView;
import application.model.Rental;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("libraryService")
public class LibraryServiceImpl implements LibraryService{

    private final LibraryDAO libraryDAO;
    private final RentalDAO rentalDAO;
    private final AppUserDAO appUserDAO;
    private final ClientDAO clientDAO;
    private final BookDAO bookDAO;

    public LibraryServiceImpl(LibraryDAO libraryDAO, RentalDAO rentalDAO, AppUserDAO appUserDAO, ClientDAO clientDAO, BookDAO bookDAO) {
        this.libraryDAO = libraryDAO;
        this.rentalDAO = rentalDAO;
        this.appUserDAO = appUserDAO;
        this.clientDAO = clientDAO;
        this.bookDAO = bookDAO;
    }

    @Override
    public List<BooksDTO> getBooks() {
        List<LibraryView> libraryViewList =  libraryDAO.getBooks();
        return getAllBooks(libraryViewList);
    }

    @Override
    public ResponseEntity<Object> borrowBook(String title) throws ParseException {

        //nie powinnam robić nowego rentala tylko odnaleźć tego stworzonego przy książce o id? i jego zaktualizować
        Book book = bookDAO.findByTitle(title);
        book.setStatus(false);
        //bookDAO.updateBook(book);

        Rental rental = rentalDAO.findByBook(book);
        Date date = new Date();
        rental.setRental_time(date);
        rental.setBook(book);
        rental.setClient(getClient());
        rentalDAO.updateRental(rental);
        getBooks();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //----------
    private List<BooksDTO> getAllBooks(List<LibraryView> libraryViewList){
        List<BooksDTO> listBooks = new ArrayList<>();
        for(LibraryView lv : libraryViewList){
            BooksDTO booksDTO = new BooksDTO();
            booksDTO.setTitle(lv.getTitle());
            booksDTO.setStatus(lv.isStatus());
            booksDTO.setRental_time(formatDate(lv.getRental_time()));
            booksDTO.setYear(lv.getYear());
            booksDTO.setEpoch(lv.getEpoch());
            booksDTO.setEmail(lv.getEmail());
            listBooks.add(booksDTO);
        }
        return listBooks;
    }

    private String formatDate(Date date){
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        if(date==null){
            return "---";
        }
        else {
            return format1.format(date);
        }
    }

    private Client getClient(){
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return clientDAO.getClientByAppUser(appUserDAO.findByEmail(myUserDetails.getUsername()));
    }
}
