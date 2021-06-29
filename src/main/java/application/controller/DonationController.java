package application.controller;

import application.dto.DonationStatisticDTO;
import application.service.DonationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DonationController {

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    private DonationService donationService;

    @GetMapping(value = "/getDonations")
    public List<DonationStatisticDTO> getAllDonations(){
        return donationService.getDonationStatistic();
    }

    @PostMapping(value = "/payDonation", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Object> payDonation(String description, String amount){
        return donationService.payDonation(description, amount);
    }
}
