package application.service;

import application.components.springSecurity.MyUserDetails;
import application.dao.AppUserDAO;
import application.dao.ClientDAO;
import application.dao.DonationDAO;
import application.dto.DonationStatisticDTO;
import application.model.Client;
import application.model.Donation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service("donationService")
public class DonationServiceImpl implements DonationService{

    private final DonationDAO donationDAO;
    private final AppUserDAO appUserDAO;
    private final ClientDAO clientDAO;
    @Resource(name = "myProps")
    private final Properties properties;

    public DonationServiceImpl(DonationDAO donationDAO, AppUserDAO appUserDAO, ClientDAO clientDAO) {
        this.donationDAO = donationDAO;
        this.appUserDAO = appUserDAO;
        this.clientDAO = clientDAO;
        properties = new Properties();
    }

    @Override
    public List<DonationStatisticDTO> getDonationStatistic() {
        List<Donation> donationList = donationDAO.getAllDonations();
        return getListDonationStatisticDTO(donationList);
    }

    @Override
    public ResponseEntity<Object> payDonation(String description, String amount) {
        Donation donation = new Donation();
        donation.setDescription(description);
        donation.setAmount(Double.parseDouble(amount));
        donation.setClient(getClient());
        Donation realisedDonation = donationDAO.addDonation(donation);
        if(realisedDonation == null){
            return new ResponseEntity<>(properties.getProperty("service.mainErrorMessage"),HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(properties.getProperty("service.donationServiceImpl.THANKS"), HttpStatus.CREATED);
        }
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

    private Client getClient(){
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return clientDAO.getClientByAppUser(appUserDAO.findByEmail(myUserDetails.getUsername()));
    }
}
