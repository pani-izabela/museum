package application.controller;

import application.dto.DonationStatisticDTO;
import application.service.DonationService;
import org.springframework.web.bind.annotation.GetMapping;
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
}
