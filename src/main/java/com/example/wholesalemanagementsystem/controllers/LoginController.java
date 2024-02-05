package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.Main;
import com.example.wholesalemanagementsystem.dao.AuthenticationService;
import com.example.wholesalemanagementsystem.dao.UserDOA;
import com.example.wholesalemanagementsystem.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;

import javafx.scene.control.TextField;

public class LoginController extends SceneController {
    @FXML
    public  TextField loginUsername;
    @FXML
    public TextField loginPassword;


    public void toSignup(ActionEvent e) throws IOException{
        super.signUpScene(e);
    }
    public void login(ActionEvent e) throws IOException {
        String username = loginUsername.getText();
        String password = loginPassword.getText();
        System.out.println(AuthenticationService.checkCredentials(username, password));
        if(AuthenticationService.checkCredentials(username, password)){
            UserDOA userDOA = new UserDOA();
            try{
                User user = userDOA.getUserByUsername(username);
                Main.setUsername(user);
                System.out.println(user.getUserName());
                userHome(e);
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }

        }
    }
}

