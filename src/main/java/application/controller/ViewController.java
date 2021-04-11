package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller //zmieniłam na @RestControler, bo miałam problem w controllersTest i to mi pomogło
//@RestController
/*ControllersTest działa tylko jeśli w ViewController używam adnotacji @RestController
* Ale jeśłi używam @RestControler mam problem ze odpalaniem widoków.
* Docelowo dobrze byłoby zmienić @Controller na @RestController - TODO*/
@Controller
public class ViewController {

    @GetMapping(value="/footer")
    public String footer(){ return "footer"; }

    @GetMapping(value="/navbar")
    public String navbar(){ return "navbar"; }

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

    @GetMapping(value = "/admin/onlyForAdmin")
    public String onlyAdmin() {
        return "admin/onlyForAdmin";
    }

    @GetMapping(value = "/postLoginUser")
    public String loggedUser() {
        return "postLoginUser";
    }
}
