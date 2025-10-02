package com.learn.first;

import java.sql.*;
import java.util.*;

public class DB {
    private static String path = "/home/o/projects/java/JSF_Tutorial/";
//    private static Connection c = null;
//    public static Connection setConnection(){
//        if (c != null){
//            return c;
//        }
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:" + path + "db1.db");
//            System.out.println("Opened database successfully");
//            return c;
//        } catch ( Exception e ) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
////            e.printStackTrace();
//            System.exit(0);
//            return null;
//        }
//    }

    public static void main(String[] args) {
        String schema = """
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
    username TEXT,
    hash TEXT NOT NULL,
    status INTEGER DEFAULT 1
);""";
        System.out.println("schema: " + schema);
        execute(schema);
        System.out.println("Table created successfully");
    }

    public static ArrayList<HashMap<String,Object>> execute(String sql, String... args) {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + path + "db1.db");
            System.out.println("Opened database successfully");
            stmt = c.prepareStatement(sql);
            int i = 1;
            for (String arg: args) {
                stmt.setString(i++, arg);
            }
            ResultSet rs = null;
            System.out.println(stmt.toString());

            var rows = new ArrayList<HashMap<String,Object>>();

            if (!stmt.execute()) {
                stmt.close();
                c.close();
                return rows;
            }

            rs = stmt.getResultSet();
            var meta = rs.getMetaData();

            int colCount = meta.getColumnCount();
            var cols = new ArrayList<String>();
            for (int index=1; index<=colCount; index++)
                cols.add(meta.getColumnName(index));

            while (rs.next()) {
                var row = new HashMap<String,Object>();
                for (String colName:cols)
                    row.put(colName, rs.getObject(colName));
                rows.add(row);
            }

            stmt.close();
            c.close();
            return rows;

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }
}