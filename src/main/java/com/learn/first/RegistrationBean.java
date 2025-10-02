package com.learn.first;

import org.mindrot.jbcrypt.BCrypt;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RegistrationBean {
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

    public String register() {
        var result = Repo.getUser(username);

        if (!result.isEmpty()) {
            message = "Username is already taken.";
            return null;
        }

        Repo.register(username, password);
        message = "Registration successful!";
        return "login";
    }
}
