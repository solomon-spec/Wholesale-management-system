package com.example.wholesalemanagementsystem.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseController {

    // this method is used to connect to the database and return the connection object
    public static Connection connect() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/javawms";
        String username = "java";
        String password = "rootroot";

        return DriverManager.getConnection(url, username, password);
    }

}


