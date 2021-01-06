package application.controller

import application.controller.MainController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
class SpringTest extends Specification{
    @Autowired
    private MainController mainController

    def "when context is loaded then all expected beans are created"() {
        expect: "the MainController is created"
        mainController != null
    }

}
