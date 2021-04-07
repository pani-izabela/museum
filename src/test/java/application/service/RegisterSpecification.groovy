package application.service

import application.dao.AppUserDAO
import application.dao.ClientDAO
import application.dao.EmployeeDAO
import application.dto.ClientRegisterDTO
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class RegisterSpecification extends Specification{

   def appDatabase = Stub(AppUserDAO.class)
   def clientDatabase = Stub(ClientDAO.class)
   def employeeDatabase = Stub(EmployeeDAO.class)
   def app = new AppUserServiceImpl(appDatabase, clientDatabase, employeeDatabase)

   def mockedAppUserDb = Mock(AppUserDAO)
   def mockedClientDb = Mock(ClientDAO)
   def mockedEmployeeDb = Mock(EmployeeDAO)
   def appMock = new AppUserServiceImpl(mockedAppUserDb, mockedClientDb, mockedEmployeeDb)


   def "method registerClient return HttpStatus Created"(){
      given: "prepare clientDTO object and return null from database"
      appDatabase.findByEmail("a.nowak@wp.pl") >> null
      def clientDTO = new ClientRegisterDTO()
      clientDTO.setFirstName("Adam")
      clientDTO.setLastName("Nowak")
      clientDTO.setEmail("a.nowak@wp.pl")
      clientDTO.setPassword("WWww!!")

      when: "call register method"
      def obj = app.registerClient(clientDTO)

      then: "check status code"
      obj.toString().contains("201 CREATED")
      then: "check status code"
      obj.getStatusCode().toString() == "201 CREATED"
   }

   def "method registerClient return ResponseEntity object"(){
      given: "prepare clientDTO object and return null from database"
      appDatabase.findByEmail("a.nowak@wp.pl") >> null
      def clientDTO = new ClientRegisterDTO()
      clientDTO.setFirstName("Adam")
      clientDTO.setLastName("Nowak")
      clientDTO.setEmail("a.nowak@wp.pl")
      clientDTO.setPassword("WWww!!")

      when: "call register method"
      def obj = app.registerClient(clientDTO)

      then: "check what object has been returned"
      obj.class == ResponseEntity
   }

   def "method findByEmail is called within method registerClient"(){
      given: "prepare clientDTO object"
      def clientDTO = new ClientRegisterDTO()
      clientDTO.setFirstName("Adam")
      clientDTO.setLastName("Nowak")
      clientDTO.setEmail("a.nowak@wp.pl")
      clientDTO.setPassword("WWww!!")

      when: "call register method"
      appMock.registerClient(clientDTO)

      then: "check if the method findByEmail has been called"
      1*mockedAppUserDb.findByEmail(clientDTO.email)
   }

}
