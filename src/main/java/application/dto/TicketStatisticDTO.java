package application.dto;

import lombok.Data;

@Data
public class TicketStatisticDTO {
    private String type;
    private int quantity;
    private double amount;
}
