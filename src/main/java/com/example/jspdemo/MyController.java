package com.example.jspdemo;

import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyController {
    @GetMapping(path = {"/","/register"})
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(Model model, @RequestParam String email, @RequestParam String password) {
        if (!Repo.exists(email)) {
            model.addAttribute("error", "1");
            return "login";
        }
        User user = Repo.getUser(email);
        if (!BCrypt.checkpw(password, user.getHash())){
            model.addAttribute("error", "1");
            return "login";
        }
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        return "loggedin";
    }

    @PostMapping(path = {"/","/register"})
    public String register(Model model, @Valid @ModelAttribute User user, BindingResult result) {
        if (user.isValid() && !result.hasErrors()) {
            user.setHash(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            Repo.addUser(user);
            return "redirect:/login";
        }
        if (!user.validPassword()){
            model.addAttribute("message", user.passwordErrors());
        }
//        model.addAttribute("errors", user.getErrors());
        return "register";
    }
}