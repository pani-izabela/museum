package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller //zmieniłam na @RestControler, bo miałam problem w controllersTest i to mi pomogło
//@RestController
@Controller
public class ViewController {

    @GetMapping(value="/footer")
    public String footer(){
        return "footer";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @GetMapping(value = "/client/register")
    public String registerClient() {
        return "client/register";
    }

    @GetMapping(value = "/employee/register")
    public String registerEmployee() { return "employee/register"; }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/passChange")
    public String passChange() {
        return "passChange";
    }
}
