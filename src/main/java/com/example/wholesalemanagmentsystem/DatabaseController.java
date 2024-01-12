package com.example.wholesalemanagmentsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseController {
    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        return DriverManager.getConnection(url, username, password);
    }
}
