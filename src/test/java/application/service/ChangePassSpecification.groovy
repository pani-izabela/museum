package application.service


import application.dao.AppUserDAO
import application.dao.ClientDAO
import application.dao.EmployeeDAO
import application.model.AppUser
import application.model.Role
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import javax.annotation.Resource

class ChangePassSpecification extends Specification{

   def appDatabase = Stub(AppUserDAO.class)
   def clientDatabase = Stub(ClientDAO.class)
   def employeeDatabase = Stub(EmployeeDAO.class)
   def app = new AppUserServiceImpl(appDatabase, clientDatabase, employeeDatabase)

   def mockedAppUserDb = Mock(AppUserDAO)
   def mockedClientDb = Mock(ClientDAO)
   def mockedEmployeeDb = Mock(EmployeeDAO)
   def appMock = new AppUserServiceImpl(mockedAppUserDb, mockedClientDb, mockedEmployeeDb)

   /*@Resource(name = "myProps")
   Properties properties = new Properties()*/
   def prop = Stub(Properties.class)

   def "method changePass return HttpStatus BAD_REQUEST when user is null"(){
      given:
      //ReflectionTestUtils.invokeMethod(app, "findAppUserByEmail") >> null
      appDatabase.findByEmail("a.nowak@wp.pl") >> null

      when:
      def obj = app.changePass("a.nowak@wp.pl", "QQqq!!")

      then:
      obj.toString().contains("400 BAD_REQUEST")
   }


   def "method changePass return HttpStatus OK when user correctly changes the password"(){
      given:
      AppUser appUser = new AppUser()
      appUser.setId(123)
      appUser.setEmail("a.nowak@wp.pl")
      appUser.setPassword("QQqq!!")
      appUser.setName("Anna")
      appUser.setSurname("Nowak")
      appUser.setAccountNonLocked(true)
      appUser.setFailedAttempt(0)
      appUser.setEnabled(true)
      List<Role> listRole = new ArrayList<Role>()
      Role role = new Role();
      role.setId(4)
      role.setName("client")
      listRole.add(role)
      appUser.setRoles(listRole)

      appDatabase.findByEmail("a.nowak@wp.pl") >> appUser

      when:
      def obj = app.changePass("a.nowak@wp.pl", "WWww!!")

      then:
      obj.toString().contains("200 OK")
   }

   def "method changePass return HttpStatus BAD_REQUEST when user get wrong pass"(){
      given:
      AppUser appUser = new AppUser()
      appUser.setId(123)
      appUser.setEmail("a.nowak@wp.pl")
      appUser.setPassword("QQqq!!")
      appUser.setName("Anna")
      appUser.setSurname("Nowak")
      appUser.setAccountNonLocked(true)
      appUser.setFailedAttempt(0)
      appUser.setEnabled(true)
      List<Role> listRole = new ArrayList<Role>()
      Role role = new Role();
      role.setId(4)
      role.setName("client")
      listRole.add(role)
      appUser.setRoles(listRole)

      appDatabase.findByEmail("a.nowak@wp.pl") >> appUser

      when:
      def obj = app.changePass("a.nowak@wp.pl", "ppp")

      then:
      obj.toString().contains("400 BAD_REQUEST")
      obj.toString().contains(prop.getProperty("service.appUserServiceImpl.WRONG_PASS"))
   }

   def "method changePass return ResponseEntity object"(){
      given:
      appDatabase.findByEmail("a.nowak@wp.pl") >> null

      when:
      def obj = app.changePass("a.nowak@wp.pl", "QQqq!!")

      then:
      obj.class == ResponseEntity
   }

}
