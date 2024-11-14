package com.example.jspdemo;

import java.sql.*;

public class Repo {
    public static void init(){
        String schema = """
CREATE TABLE IF NOT EXISTS users (
id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
hash TEXT NOT NULL,
firstName TEXT,
lastName TEXT,
email TEXT,
birthYear INTEGER)""";
        System.out.println("schema: " + schema);
        execute(schema);
        System.out.println("Table created successfully");
    }

    public static ResultSet execute(String sql, String... args) {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db1.db");
            System.out.println("Opened database successfully");

            stmt = c.prepareStatement(sql);
            int i = 1;
            for (String arg : args) {
                stmt.setString(i++, arg);
            }
            ResultSet rs = null;
            if (stmt.execute()) {
                rs = stmt.getResultSet();
            }
            stmt.close();
            c.close();
            return rs;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return null;
        }
    }
}