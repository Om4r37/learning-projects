package com.example.todo.controller;

import com.example.todo.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.todo.CookieSigner.createIdCookie;

@Controller
public class Register {
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public static String register(@ModelAttribute("user") User user, HttpServletResponse response) {
        String userId = String.valueOf(user.insert());
        return createIdCookie(response, userId);
    }
}