package application.dao;

import application.model.Donation;

import java.util.List;

public interface DonationDAO {
    List<Donation> getAllDonations();
    Donation addDonation(Donation donation);
}
