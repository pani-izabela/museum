package application.service

import application.dao.AppUserDAO
import application.dao.ClientDAO
import application.dao.EmployeeDAO
import application.dto.ClientRegisterDTO
import org.springframework.http.ResponseEntity
import spock.lang.Specification
import spock.lang.Unroll

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
      given:
      appDatabase.findByEmail("a.nowak@wp.pl") >> null
      def clientDTO = new ClientRegisterDTO()
      clientDTO.setFirstName("Adam")
      clientDTO.setLastName("Nowak")
      clientDTO.setEmail("a.nowak@wp.pl")
      clientDTO.setPassword("WWww!!")

      when:
      def obj = app.registerClient(clientDTO)

      then:
      obj.toString().contains("201 CREATED")
      then:
      obj.getStatusCode().toString() == "201 CREATED"
   }

   def "method registerClient return ResponseEntity object"(){
      given:
      appDatabase.findByEmail("a.nowak@wp.pl") >> null
      def clientDTO = new ClientRegisterDTO()
      clientDTO.setFirstName("Adam")
      clientDTO.setLastName("Nowak")
      clientDTO.setEmail("a.nowak@wp.pl")
      clientDTO.setPassword("WWww!!")

      when:
      def obj = app.registerClient(clientDTO)

      then:
      obj.class == ResponseEntity
   }

   def "mock test"(){
      given:
      def clientDTO = new ClientRegisterDTO()
      clientDTO.setFirstName("Adam")
      clientDTO.setLastName("Nowak")
      clientDTO.setEmail("a.nowak@wp.pl")
      clientDTO.setPassword("WWww!!")

      when:
      appMock.registerClient(clientDTO)

      then:
      1*mockedAppUserDb.findByEmail(clientDTO.email)
   }

}