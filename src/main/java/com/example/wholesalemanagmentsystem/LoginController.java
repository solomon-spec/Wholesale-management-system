package com.example.wholesalemanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    public  TextField loginUsername;
    @FXML
    public TextField loginPassword;


    public void signUp(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signup.fxml")));
        Stage stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private static boolean checkCredentials(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
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

    public void login(ActionEvent e) throws IOException {
        String username = loginUsername.getText();
        String password = loginPassword.getText();

        System.out.println(checkCredentials(username, password));
    }
}

