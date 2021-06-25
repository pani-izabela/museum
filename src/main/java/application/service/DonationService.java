package application.service;

import application.dto.DonationStatisticDTO;

import java.util.List;

public interface DonationService {
    List<DonationStatisticDTO> getDonationStatistic();
}
