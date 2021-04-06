package application.service

import application.dao.AppUserDAO
import application.dao.ClientDAO
import application.dao.EmployeeDAO
import application.dto.ClientRegisterDTO
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class ChangePassSpecification extends Specification{

   def appDatabase = Stub(AppUserDAO.class)
   def clientDatabase = Stub(ClientDAO.class)
   def employeeDatabase = Stub(EmployeeDAO.class)
   def app = new AppUserServiceImpl(appDatabase, clientDatabase, employeeDatabase)

   def mockedAppUserDb = Mock(AppUserDAO)
   def mockedClientDb = Mock(ClientDAO)
   def mockedEmployeeDb = Mock(EmployeeDAO)
   def appMock = new AppUserServiceImpl(mockedAppUserDb, mockedClientDb, mockedEmployeeDb)

   /*def "mock test 1"(){
      given:

      when:
      //mockedAppUserDb.findByEmail("s.nowak@wp.pl")
      app.changePass("s.nowak@wp.pl", "QQqq!!")

      then:
      1*mockedAppUserDb.findByEmail("s.nowak@wp.pl")

   }*/


   /*def "method changePass return HttpStatus BAD_REQUEST"(){
      given:
      appDatabase.findByEmail("a.nowak@wp.pl") >> null

      when:
      def obj = app.changePass("a.nowak@wp.pl", "QQqq!!")

      then:
      obj.toString().contains("204 BAD_REQUEST")
   }*/

   /*def "method registerClient return ResponseEntity object"(){
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
   }*/

}
