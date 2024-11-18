package com.learn.first;

import org.mindrot.jbcrypt.BCrypt;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LoginBean {
    private String username;
    private String password;
    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String login() {
        var result = Repo.getUser(username);

        if (result.isEmpty()) {
            message = "Invalid username or password.";
            return null;
        }

        String storedHash = (String) result.get(0).get("hash");

        if (BCrypt.checkpw(password, storedHash)) {
            message = "Login successful!";
            return "redirect:home";
        } else {
            message = "Invalid username or password.";
            return null;
        }
    }
}
