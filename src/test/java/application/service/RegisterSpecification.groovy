package application.service

import application.dao.AppUserDAO
import application.dao.ClientDAO
import application.dao.EmployeeDAO
import application.dto.ClientRegisterDTO
import application.model.AppUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import spock.lang.Specification
import spock.lang.Unroll

class RegisterSpecification extends Specification{

   def database = Stub(AppUserDAO.class)
   def clientDatabase = Stub(ClientDAO.class)
   def employeeDatabase = Stub(EmployeeDAO.class)
   def app = new AppUserServiceImpl(database, clientDatabase, employeeDatabase)


   def "sample test"(){
      given:
      database.findByEmail("a.nowak@wp.pl") >> null
      def clientDTO = new ClientRegisterDTO()
      clientDTO.setFirstName("Adam")
      clientDTO.setLastName("Nowak")
      clientDTO.setEmail("a.nowak@wp.pl")
      clientDTO.setPassword("WWww!!")

      when:
      def obj = app.registerClient(clientDTO)

      then:
      //obj.class == ResponseEntity
      obj.toString().contains("201 CREATED")

   }

}
