package application.service;

import application.dao.DonationDAO;
import application.dto.DonationStatisticDTO;
import application.model.Donation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("donationService")
public class DonationServiceImpl implements DonationService{

    private final DonationDAO donationDAO;

    public DonationServiceImpl(DonationDAO donationDAO) {
        this.donationDAO = donationDAO;
    }

    @Override
    public List<DonationStatisticDTO> getDonationStatistic() {
        List<Donation> donationList = donationDAO.getAllDonations();
        return getListDonationStatisticDTO(donationList);
    }

    //---------------------------prywatne

    private List<DonationStatisticDTO> getListDonationStatisticDTO(List<Donation> donationList){
        List<DonationStatisticDTO> listDonationStatistic = new ArrayList<>();
        for (Donation donation : donationList) {
            DonationStatisticDTO donationStatisticDTO = new DonationStatisticDTO();
            donationStatisticDTO.setAmount(donation.getAmount());
            donationStatisticDTO.setDescription(donation.getDescription());
            donationStatisticDTO.setEmail(donation.getClient().getAppUser().getEmail());
            listDonationStatistic.add(donationStatisticDTO);
        }
        return listDonationStatistic;
    }
}
