package application.service

import application.dao.AppUserDAO
import application.dao.ClientDAO
import application.dao.DonationDAO
import application.dao.ReserveDAO
import application.model.Donation
import application.model.Reserve
import spock.lang.Specification

class DonationSpecification extends Specification{

    def donationDatabase = Stub(DonationDAO.class)
    def clientDatabase = Stub(ClientDAO.class)
    def appUserDatabase = Stub(AppUserDAO.class)
    def reserveDatabase = Stub(ReserveDAO.class)

    def donationService = new DonationServiceImpl(donationDatabase, appUserDatabase, clientDatabase, reserveDatabase)

    def "method fundReserve increases the reserve of the museum"() {
        given: "prepare data for reserve"
        Reserve reserve1 = new Reserve();
        reserve1.setKey("kwota")
        reserve1.setAmount(1000.00)
        reserveDatabase.getReserveByKey("kwota") >> reserve1

        Reserve reserve2 = new Reserve();
        reserve2.setKey("kwota")
        reserve2.setAmount(1400.00)

        reserveDatabase.updateReserve(reserve1, 1400.00) >> reserve2

        when: "call fundReserve method"
        def obj = donationService.fundReserve("400.00")

        then: "check museum reserve"
        obj.body == "Aktualna rezerwa Muzeum wynosi: 1400.0"
    }
}
