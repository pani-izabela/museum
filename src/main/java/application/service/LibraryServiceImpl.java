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
import org.thymeleaf.util.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public ResponseEntity<Object> borrowBook(String title) {
        Book book = bookDAO.findByTitle(title);
        book.setStatus(false);
        Rental rental = rentalDAO.findByBook(book);
        rental.setRental_time(getBorrowingData());
        rental.setBook(book);
        rental.setClient(getClient());
        rentalDAO.updateRental(rental);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Object> checkBook(String title) {
        Book book = bookDAO.findByTitle(title);
        Date actualDate = new Date();
        Date deadlineForReturn = rentalDAO.findByBook(book).getRental_time();

        long diffInMillies = Math.abs(deadlineForReturn.getTime() - actualDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if(diff<=3){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return null;
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

    private Date getBorrowingData(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    private Client getClient(){
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return clientDAO.getClientByAppUser(appUserDAO.findByEmail(myUserDetails.getUsername()));
    }
}
