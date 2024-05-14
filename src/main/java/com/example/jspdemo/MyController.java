package com.example.jspdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {

    @RequestMapping(path = {"/","/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("message", "Hello World!");
        return "home";
    }
}
