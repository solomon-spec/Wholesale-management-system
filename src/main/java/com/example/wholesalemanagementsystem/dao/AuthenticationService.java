package com.example.wholesalemanagementsystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationService {
    public static boolean checkCredentials(String username, String password) {
        try {
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement statement = DatabaseController.connect().prepareStatement(query);

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean RegisterUser(String username, String password, String email, String first_name, String last_name, String gender){

        try {
            String query = "INSERT INTO user (username, password, email, first_name, last_name, gender) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = DatabaseController.connect().prepareStatement(query);

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            statement.setString(4, first_name);
            statement.setString(5, last_name);
            statement.setString(6,gender);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    // check if username is unique
    public static boolean checkUsername(String username) {
        try {
            String query = "SELECT * FROM user WHERE username = ?";
            PreparedStatement statement = DatabaseController.connect().prepareStatement(query);

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    // check if email is unique
    public static boolean checkEmail(String email) {
        try {
            String query = "SELECT * FROM user WHERE email = ?";
            PreparedStatement statement = DatabaseController.connect().prepareStatement(query);

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean checkAdmin(String username) throws SQLException {
        String query = "SELECT * FROM user WHERE username = ? AND isAdmin = 1";
        PreparedStatement statement = DatabaseController.connect().prepareStatement(query);

        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }


}
