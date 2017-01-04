package ua.com.codespace.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping({"/", "/welcome"})
public class WelcomeController {

    @RequestMapping(method = GET)
    public String welcome() {
        return "welcome";
    }



}
