package com.example.todo.controller;

import com.example.todo.CookieSigner;
import com.example.todo.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdatePassword {
    @GetMapping("/update-password")
    public String updatePassword(Model model) {
        model.addAttribute("user", new User());
        return "updatePassword";
    }

    @PostMapping("/update-password")
    public static String updatePassword(@ModelAttribute("user") User user, HttpServletResponse response, @CookieValue(name = "id", defaultValue = "0") String id) {
        if (id.equals("0") || !CookieSigner.verify(id.split(":")[0], id.split(":")[1])) return "redirect:/login";
        User.updatePassword(user.getPassword(), id.split(":")[0]);
        return "redirect:/";
    }
}
