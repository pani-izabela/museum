package application.service

import application.dao.AppUserDAO
import application.model.AppUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class AppUserServiceSpecification extends Specification{

    @Autowired
    AppUserServiceImpl appUserService;
    @Autowired
    AppUserDAO appUserDAO;

    def setup(){
        appUserService = new AppUserServiceImpl();
        appUserDAO = Stub(AppUserDAO);
        appUserService.setAppUserDAO(appUserDAO);

        appUserDAO.findById(_ as int) >> { int id ->
            if(id==1){
                AppUser ap1 = new AppUser();
                ap1.setId(1);
                ap1.setEmail("izabela.lach86@gmail.com");
                ap1.setPassword("QQqq!!");
                return  ap1;
            }
            if(id==2){
                AppUser ap2 = new AppUser();
                ap2.setId(2);
                ap2.setEmail("genek.loska@wp.pl");
                ap2.setPassword("WWww!!");
                return  ap2;
            }
        }
    }

    def "return appUser object is not null"(){
        expect :
        appUserService.getAppUser(id) != null
        where :
        id << [1, 2]
    }

}
