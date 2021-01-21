package application.controller


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SpringTest extends Specification{
    @Autowired
    private ViewController viewController
    @Autowired
    private HelloController helloController

    def "when context is loaded then ViewController bean is created"() {
        expect: "the ViewController is created"
        viewController != null
    }

    def "when context is loaded then HelloController bean is created"() {
        expect: "the HelloController is created"
        helloController != null
    }
}
