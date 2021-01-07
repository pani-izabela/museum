package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller //zmieniłam na @RestControler, bo miałam problem w controllersTest i to mi pomogło
@RestController
public class MainController {
    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }
}
