package application.service

import application.dao.AppUserDAO
import application.dao.ClientDAO
import application.dao.EmployeeDAO
import application.dto.ClientRegisterDTO
import application.model.AppUser
import application.model.Role
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import spock.lang.Specification
import spock.lang.Unroll

class LoginSpecification extends Specification{

   def appDatabase = Stub(AppUserDAO.class)
   def clientDatabase = Stub(ClientDAO.class)
   def employeeDatabase = Stub(EmployeeDAO.class)
   def app = new AppUserServiceImpl(appDatabase, clientDatabase, employeeDatabase)


   def "method loadUserByUsername return correct data of logged user"(){
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
      def obj = app.loadUserByUsername("a.nowak@wp.pl")

      then:
      obj.toString().contains(appUser.email)
      then:
      obj.class == User
      then:
      obj.getUsername() == "a.nowak@wp.pl"
      then:
      obj.getAuthorities().size() == 1
   }

   def "method loadUserByUsername return exception when user is null"(){
      given:
      appDatabase.findByEmail("a.nowak@wp.pl") >> null

      when:
      app.loadUserByUsername("a.nowak@wp.pl")

      then:
      thrown(UsernameNotFoundException.class)
   }

}
