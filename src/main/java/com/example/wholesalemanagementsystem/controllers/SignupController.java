package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.dao.AuthenticationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignupController extends SceneController {
    @FXML
    public TextField firstName, lastName, userName,  email;
    @FXML
    public PasswordField password,confirmPassword;
    @FXML
    public CheckBox termsAndConditions;
    @FXML
    public RadioButton male, female;

    public void signUp(ActionEvent e) throws IOException {

        if(firstName.getText().isEmpty() || lastName.getText().isEmpty() ||
                userName.getText().isEmpty() || password.getText().isEmpty() ||
                confirmPassword.getText().isEmpty() || email.getText().isEmpty() ||
                termsAndConditions.getText().isEmpty() ||
                (!male.isSelected() && !female.isSelected())) {
            System.out.println("Please fill all fields");
        }
        else if(!password.getText().equals(confirmPassword.getText())) {
            System.out.println("Password and Confirm Password do not match");
        }
        else if(!termsAndConditions.isSelected()) {
            System.out.println("Please accept terms and conditions");
        }
        else if(AuthenticationService.checkUsername(userName.getText())) {
            System.out.println("Username already exists");
        }
        else if(AuthenticationService.checkEmail(email.getText())) {
            System.out.println("Email already exists");
        }
        else {
            AuthenticationService.RegisterUser(userName.getText(),password.getText(),email.getText(), firstName.getText(),lastName.getText(),"male");
            System.out.println("User added successfully");
            super.loginScene(e);
        }
    }
}
