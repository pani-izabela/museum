package application.service;

import application.dao.DonationDAO;
import application.dto.DonationStatisticDTO;
import application.model.Client;
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
        List<DonationStatisticDTO> listDonationStatistic = new ArrayList<>();
        List<Donation> donationList = donationDAO.getAllDonations();
        for(int i=1; i<=donationList.size(); i++) {
            addPositionToList(listDonationStatistic, donationList);
        }
        return listDonationStatistic;
    }

    //------------------------------------------------------------prywatne

    private void addPositionToList(List<DonationStatisticDTO> listDonationStatistic, List<Donation> donationList){
        listDonationStatistic.add(getDonationStatisticDTO(donationList));
    }

    private DonationStatisticDTO getDonationStatisticDTO(List<Donation> donationList){
        DonationStatisticDTO donationStatisticDTO = new DonationStatisticDTO();
        for (Donation donation : donationList) {
            donationStatisticDTO.setAmount(donation.getAmount());
            donationStatisticDTO.setDescription(donation.getDescription());
            donationStatisticDTO.setEmail(donation.getClient().getAppUser().getEmail());
            donationStatisticDTO.setLp(donationList.size());
        }
        return donationStatisticDTO;
    }
}
