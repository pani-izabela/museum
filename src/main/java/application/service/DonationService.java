package application.service;

import application.dto.DonationStatisticDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DonationService {
    List<DonationStatisticDTO> getDonationStatistic();
    ResponseEntity<Object> payDonation(String description, String amount);
}
