package com.example.jspdemo;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    CustomerRepo customerRepo;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password) {
        List<Customer> customers = customerRepo.findByUsername(username);

        if (customers.isEmpty() || !BCrypt.checkpw(password, customers.getFirst().getHash())) {
            model.addAttribute("error", "1");
            return "login";
        }
        model.addAttribute("username", username);
        return "loggedin";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(Model model,
                            @RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password) {
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        customerRepo.save(new Customer(username, hash));
        System.out.println(username + "\n" + password + "\n" + hash);
        return "login";
    }
}
