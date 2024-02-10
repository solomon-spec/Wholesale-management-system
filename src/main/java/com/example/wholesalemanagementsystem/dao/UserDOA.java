package com.example.wholesalemanagementsystem.dao;

import com.example.wholesalemanagementsystem.Main;
import com.example.wholesalemanagementsystem.models.User;
import javafx.event.ActionEvent;

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
        String query = "SELECT * FROM user WHERE user_id = ?";
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
    public User getUserByUsername(String username) throws  SQLException{
        connection = DatabaseController.connect();
        String query = "SELECT * FROM user  WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
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
        String query = "SELECT * FROM user";
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
        String query = "UPDATE user SET username = ?, password = ?, email = ?, first_name = ?, last_name = ?, gender = ?, is_admin = ? WHERE user_id = ?";
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
    public boolean updateUserDetails( String username, String password, String email, String firstname, String lmane, String gender) throws SQLException {
        connection = DatabaseController.connect();
        String query = "UPDATE user SET password = ?, email = ?, first_name = ?, last_name = ?, gender = ? WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, password);
        statement.setString(2, email);
        statement.setString(3, firstname);
        statement.setString(4, lmane);
        statement.setString(5, gender);
        statement.setString(6, username);

        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }


    // delete user by id
    public boolean deleteUser(int id) throws SQLException {
        connection = DatabaseController.connect();
        String query = "DELETE FROM user WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    }

    public boolean makeAdmin(String username, String password) throws SQLException {
        connection = DatabaseController.connect();
        // if current user is admin and have correct password then make user admin

        if(Main.getUsername().getIsAdmin() && Main.getUsername().getPassword().equals(password)){
            String query = "UPDATE user SET is_admin = 1 WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(username));
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        else{
            return false;
        }
    }


}
