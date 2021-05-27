package application.service

import application.components.springSecurity.CustomAuthenticationSuccessHandler
import application.dao.AppUserDAO
import application.dao.ClientDAO
import application.dao.MuseumFinanceDAO
import application.dao.TicketDAO
import application.model.AppUser
import application.model.Client
import application.model.MuseumFinance
import application.model.Ticket
import spock.lang.Specification

class TicketSpecification extends Specification{

    def clientDatabase = Stub(ClientDAO.class)
    def ticketDatabase = Stub(TicketDAO.class)
    def museumFinanceDatabase = Stub(MuseumFinanceDAO.class)
    def authenticationSuccess = Stub(CustomAuthenticationSuccessHandler.class)

    def ticketService = new TicketServiceImpl(museumFinanceDatabase, clientDatabase, ticketDatabase, authenticationSuccess)

    def "method fundAccount increases the finances of the museum"(){
        given: "prepares data on the finances of the museum"
        MuseumFinance museumFinance1 = new MuseumFinance()
        museumFinance1.setKey("kwota")
        museumFinance1.setAmount(1000.00)
        museumFinanceDatabase.getFinanceByKey("kwota") >> museumFinance1

        MuseumFinance museumFinance2 = new MuseumFinance()
        museumFinance2.setKey("kwota")
        museumFinance2.setAmount(1200.00)

        museumFinanceDatabase.updateAmount(museumFinance1, 1200.00) >> museumFinance2


        when: "call fundAmount method"
        def obj = ticketService.fundAccount("200.00")

        then: "chceck museum finances"
        obj.body == "Aktualne finanse Muzeum to: 1200.0"

    }

    //rzeby wyszło muszę sprawdzić jak zamockować uzywaną metodę pobierania zalogowanego użytkownika
    /*def "method addTicket return HttpStatus OK"(){
        given: "prepare list of tickets"
        def appUser = new AppUser()
        appUser.setId(11)
        appUser.setName('Anna')
        appUser.setSurname('Nowak')
        appUser.setEmail("a.nowak@wp.pl")
        appUser.setPassword('QQqq!!')
        authenticationSuccess.getAppUser() >> appUser

        def client = new Client()
        client.setId(12)
        client.setAppUser(appUser)

        def ticketsList = new ArrayList()
        client.getAppUser() >> appUser

        def ticket1 = new Ticket()
        ticket1.setId(1)
        ticket1.setType("normal")
        ticket1.setPrice(100.00)
        def ticket2 = new Ticket()
        ticket2.setId(2)
        ticket2.setType("normal")
        ticket2.setPrice(100.00)
        def ticket3 = new Ticket()
        ticket3.setId(3)
        ticket3.setType("normal")
        ticket3.setPrice(100.00)
        def ticket4 = new Ticket()
        ticket4.setId(3)
        ticket4.setType("normal")
        ticket4.setPrice(100.00)

        //def ticketsList = new ArrayList()
        ticketsList.add(ticket1)
        ticketsList.add(ticket2)
        ticketsList.add(ticket3)
        ticketsList.add(ticket4)

        client.setTickets(ticketsList)


        when: "call addTicket method"
        ticketService.getClient() >> client
        def obj = ticketService.addTicket(ticketsList)

        then: "check status code"
        obj.toString().contains("200 OK")
        then: "check status code"
        obj.getStatusCode().toString() == "200 OK"
    }*/
}
