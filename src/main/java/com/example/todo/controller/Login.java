package com.example.todo.controller;

import com.example.todo.DB;
import com.example.todo.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.mindrot.jbcrypt.BCrypt;

import static com.example.todo.CookieSigner.createIdCookie;

@Controller
public class Login {
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public static String login(@ModelAttribute("user") User user, HttpServletResponse response) {
        var loginUser = DB.execute("SELECT * FROM users WHERE username = ?", user.getUsername());
        if (loginUser == null
                || loginUser.isEmpty()
                || !BCrypt.checkpw(user.getPassword(), loginUser.getFirst().get("hash").toString())
        ) return "loginFailed";

        String userId = loginUser.getFirst().get("id").toString();
        return createIdCookie(response, userId);
    }
}