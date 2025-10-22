package com.example.todo;

import java.sql.*;
import java.util.*;

public class DB {
    private static String path = "";

    public static void init() {
        String schema = """
CREATE TABLE IF NOT EXISTS tasks (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT not null,
    user_id INTEGER,
    status TEXT DEFAULT 'pending',
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
    username TEXT,
    hash TEXT
);""";
        for (String statement : schema.split(";")) execute(statement);
    }

    public static ArrayList<HashMap<String,Object>> execute(String sql, String... args) {
        Connection c = null;
        PreparedStatement stmt = null;
        var rows = new ArrayList<HashMap<String,Object>>();
        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + path + "db1.db");
//            System.out.println("Opened database successfully");
            stmt = c.prepareStatement(sql);
            int i = 1;
            for (String arg: args) stmt.setString(i++, arg);
            ResultSet rs = null;
            System.out.println("\u001B[34m" + stmt.toString() + "\u001B[0m");
            if (stmt.execute()) {
                rs = stmt.getResultSet();
                var meta = rs.getMetaData();
                int colCount = meta.getColumnCount();
                var cols = new ArrayList<String>();
                for (int index=1; index<=colCount; index++) cols.add(meta.getColumnName(index));
                while (rs.next()) {
                    var row = new HashMap<String,Object>();
                    for (String colName:cols) row.put(colName, rs.getObject(colName));
                    rows.add(row);
                }
            }
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            e.printStackTrace();
        }
        return rows;
    }
}

