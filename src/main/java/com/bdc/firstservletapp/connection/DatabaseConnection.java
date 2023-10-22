package com.bdc.firstservletapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bdc_course";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "@dolphin888";

    // connect to the database using the credentials and return a connection object
    public Connection connection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
