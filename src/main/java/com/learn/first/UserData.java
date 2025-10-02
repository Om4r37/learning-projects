package com.learn.first;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userData", eager = true)
@SessionScoped
public class UserData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private int status;
    private static ArrayList<User> users;
    private String message = "";

    public ArrayList<User> getUsers() {
        users = Repo.getAllUsers();
        return users;
    }

    public String addUser() {
        User user = new User(username, status);
        users.add(user);
        return null;
    }

    public String deleteUser(User user) {
        users.remove(user);
        return null;
    }

    public void save(){
        if (valid()){
            Repo.updateUsers(users);
            message = "User data saved";
            users = Repo.getAllUsers();
        }
        else message = "Invalid user data";
    }

    public boolean valid(){
        for (User user : users) {
            int status = user.getStatus();
            if (status != 1 && status != 2) return false;
        }
        return true;
    }
    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
}
