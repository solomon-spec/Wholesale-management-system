package com.example.wholesalemanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
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
    }
}

