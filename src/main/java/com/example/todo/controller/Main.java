package com.example.todo.controller;

import com.example.todo.CookieSigner;
import com.example.todo.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {
    @GetMapping("/")
    public String index(Model model, @CookieValue(name = "id", defaultValue = "0") String id) {
        if (id.equals("0") || !CookieSigner.verify(id.split(":")[0], id.split(":")[1])) return "redirect:/login";
        User user;
        try {
            user = new User(id);
            model.addAttribute("username", user.getUsername());
            return "redirect:/tasks";
        }  catch (NullPointerException e) {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public static String logout(HttpServletResponse response) {
        Cookie userCookie = new Cookie("id", null);
        userCookie.setMaxAge(0);
        userCookie.setPath("/");
        response.addCookie(userCookie);
        return "redirect:/";
    }

    @GetMapping("/delete-account")
    public String deleteAccount(@CookieValue(name = "id", defaultValue = "0") String id) {
        if (id.equals("0") || !CookieSigner.verify(id.split(":")[0], id.split(":")[1])) return "redirect:/login";
        User.delete(id.split(":")[0]);
        return "redirect:/logout";
    }
}