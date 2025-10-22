package com.example.todo;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    int id;
    String username;
    String password;

    public User() {}

    public User(String id) {
        String[] parts = id.split(":");
        String userId = parts[0];
        String signature = parts[1];
        if (!CookieSigner.verify(userId, signature)) throw  new RuntimeException("Invalid user ID Signature");
        var user = DB.execute("SELECT * FROM users WHERE id = ?", userId);
        this.id = Integer.parseInt(userId);
        this.username = user.getFirst().get("username").toString();
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    public int insert(){
        DB.execute(
                "INSERT INTO users (username, hash) VALUES (?, ?)",
                username,
                BCrypt.hashpw(password, BCrypt.gensalt())
        );
        String query = "SELECT * FROM users WHERE username = ?";
        return Integer.parseInt(DB.execute(query, username).getFirst().get("id").toString());
    }

    public static void updatePassword(String newPassword, String id) {
        DB.execute("UPDATE users SET hash = ? WHERE id = ?", BCrypt.hashpw(newPassword, BCrypt.gensalt()), id);
    }

    public static void delete(String id) {
        DB.execute("DELETE FROM users WHERE id = ?", id);
    }
}
