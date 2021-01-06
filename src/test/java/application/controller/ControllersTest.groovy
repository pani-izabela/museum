package application.controller


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@AutoConfigureMockMvc
@WebMvcTest
class ControllersTest extends Specification{
    @Autowired
    private MockMvc mvc

    def "/hello returns hello world!"(){
        expect:
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/hello"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().response.contentAsString.toLowerCase() == "hello world!"
    }

    def "when get is performed then the response has status 200 and content is 'index'"() {
        expect: "Status is 200 and the response is 'Hello world!'"
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080//index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().response.contentAsString == "index"
    }
}
