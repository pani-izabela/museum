package application.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BooksDTO {

    private String title;
    private boolean status;
    private String rental_time;
    private String year;
    private String epoch;
    private String email;
}
