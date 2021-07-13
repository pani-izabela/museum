package application.service;

import application.dao.LibraryDAO;
import application.dto.BooksDTO;
import application.model.LibraryView;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("libraryService")
public class LibraryServiceImpl implements LibraryService{

    private final LibraryDAO libraryDAO;

    public LibraryServiceImpl(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }

    @Override
    public List<BooksDTO> getBooks() {
        List<LibraryView> libraryViewList = libraryDAO.getBooks();
        return getAllBooks(libraryViewList);
    }


    //----------
    private List<BooksDTO> getAllBooks(List<LibraryView> libraryViewList){
        List<BooksDTO> listBooks = new ArrayList<>();
        for(LibraryView lv : libraryViewList){
            BooksDTO booksDTO = new BooksDTO();
            booksDTO.setTitle(lv.getTitle());
            booksDTO.setStatus(lv.isStatus());
            booksDTO.setRental_time(lv.getRental_time());
            booksDTO.setYear(lv.getYear());
            booksDTO.setEpoch(lv.getEpoch());
            booksDTO.setEmail(lv.getEmail());
            listBooks.add(booksDTO);
        }
        return listBooks;
    }

    /*private String calculateDateLoanTerm(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, numberOfLoanInstallment);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        date = format1.format(calendar.getTime());
        return date;
    }*/
}
