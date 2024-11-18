package com.learn.first;

import org.mindrot.jbcrypt.BCrypt;

import java.util.*;

public class Repo {
    public static ArrayList<HashMap<String,Object>> getUser(String username){
        String query = "SELECT * FROM users WHERE username = ?";
        return DB.execute(query, username);
    }

    public static ArrayList<HashMap<String,Object>> register(String username, String password){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String insertQuery = "INSERT INTO users (username, hash) VALUES (?, ?);";
        return DB.execute(insertQuery, username, hashedPassword);
    }

    public static ArrayList<User> getAllUsers(){
        String query = "SELECT * FROM users;";
        var rs = DB.execute(query);
        var users = new ArrayList<User>();
        for(var r : rs){
            int status = r.get("status") == null ? 0 : (int) r.get("status");
            var user = new User((String) r.get("username"), status);
            users.add(user);
        }
        return users;
    }

    public static void updateUsers(ArrayList<User> users){
        String query = "UPDATE users SET status = ? WHERE username = ?";
        for (User user : users) {
            DB.execute(query, String.valueOf(user.getStatus()), user.getUsername());
        }
    }
}
