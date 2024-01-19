package com.example.wholesalemanagmentsystem.dao;

import com.example.wholesalemanagmentsystem.controllers.DatabaseController;
import com.example.wholesalemanagmentsystem.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDOA {

    private Connection connection;

    // get user by id
    public User getUserById(int id)  throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            User user = new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("gender"),
                    resultSet.getBoolean("is_admin"),
                    new CartDAO().getUserCart(resultSet.getInt("user_id"))
            );
            return user;
        }

        return null;
    }
    // get all users
    public ArrayList<User> getAllUser() throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM users";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<User> users = new ArrayList<>();

        while (resultSet.next()){
            // add user to array list

            users.add(new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("gender"),
                    resultSet.getBoolean("is_admin"),
                    new CartDAO().getUserCart(resultSet.getInt("user_id"))
            ) );
        }
        return users;
    }

    // update user by id

    public boolean updateUser(User user) throws SQLException {
        connection = DatabaseController.connect();
        String query = "UPDATE User SET username = ?, password = ?, email = ?, first_name = ?, last_name = ?, gender = ?, is_admin = ? WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getFirstName());
        statement.setString(5, user.getLastName());
        statement.setString(6, user.getGender());
        statement.setBoolean(7, user.getIsAdmin());
        statement.setInt(8, user.getUserId());
        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }

    // delete user by id
    public boolean deleteUser(int id) throws SQLException {
        connection = DatabaseController.connect();
        String query = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    }

}
