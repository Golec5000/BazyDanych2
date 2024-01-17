package org.application.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static DatabaseConnection instance;

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) instance = new DatabaseConnection();
        return instance;
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}