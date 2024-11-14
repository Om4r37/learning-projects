package com.example.jspdemo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Repo {
    public static User getUser(String email) {
        ResultSet rs = DB.execute("SELECT * FROM users WHERE email = ?;", email);
        User user = new User();
        try {
            user.setEmail(rs.getString("email"));
            user.setHash(rs.getString("hash"));
            user.setBirthYear(rs.getInt("birthYear"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    };
    public static boolean exists(String email) {
        ResultSet rs = DB.execute("SELECT * FROM users WHERE email = ?;", email);
        boolean exists = false;
        try {
            exists = rs.next();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exists;
    }

    public static void addUser(User user) {
        DB.execute(
                "INSERT INTO users (email, hash, firstName, lastName, birthYear) VALUES (?, ?, ?, ?, ?);",
                user.getEmail(),
                user.getHash(),
                user.getFirstName(),
                user.getLastName(),
                String.valueOf(user.getBirthYear()));
    }
}
