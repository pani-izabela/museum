package application.dto;

import lombok.Data;

@Data
public class DonationStatisticDTO {

    private int lp;
    private String description;
    private double amount;
    private String email;
}
