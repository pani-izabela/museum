package application.service

import application.components.springSecurity.MyUserDetails
import application.dao.AppUserDAO
import application.dao.ClientDAO
import application.dao.EmployeeDAO
import application.dto.AppUserRegisterDTO
import application.model.AppUser
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Specification

class ProfileSpecification extends Specification{

    def appDatabase = Stub(AppUserDAO.class)
    def clientDatabase = Stub(ClientDAO.class)
    def employeeDatabase = Stub(EmployeeDAO.class)
    def appUserService = new AppUserServiceImpl(appDatabase, clientDatabase, employeeDatabase)
    def authentication = Stub(Authentication.class)

    def "method changeDataOfUser allows to change the surname of appUser"(){
        given: "prepare data for AppUserRegisterDTO and AppUser"
        def appUserDTO = new AppUserRegisterDTO()
        appUserDTO.setEmail('a.nowak@wp.pl')
        appUserDTO.setFirstName('Anna')
        appUserDTO.setLastName('Kowalska')
        appUserDTO.setPassword('QQqq!!')

        def appUser = new AppUser()
        appUser.setId(11)
        appUser.setEmail('a.nowak@wp.pl')
        appUser.setName('Anna')
        appUser.setSurname('Nowak')
        appUser.setPassword('QQqq!!')

        def myUserDetail = new MyUserDetails(appUser)
        authentication.getPrincipal() >> myUserDetail
        SecurityContextHolder.getContext().setAuthentication(authentication);
        appDatabase.findByEmail(myUserDetail.getUsername()) >> appUser

        when: "surname change"
        def objResult = appUserService.changeDataOfUser(appUserDTO)

        then: "surname of AppUser has been changed"
        //objResult.body == "a.nowak@wp.pl twoje dane zostały zmienione"
        objResult.toString().contains(appUserDTO.getLastName())
        !objResult.toString().contains('Nowak')
    }

}
