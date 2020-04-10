package com.company;

import java.sql.Connection;
import java.sql.DriverManager;

public final class Database {
    private static Database db;
    private  Connection connect;

    private Database() {
        try {
            connect = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "dbauser", "sql");
            System.out.print("Connected");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Database getInstance() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    public Connection getConnection() {
        return connect;
    }
}

