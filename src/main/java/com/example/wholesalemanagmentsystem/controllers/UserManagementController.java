package com.example.wholesalemanagmentsystem.controllers;

import com.example.wholesalemanagmentsystem.dao.UserDOA;
import com.example.wholesalemanagmentsystem.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class UserManagementController extends SceneController implements Initializable {
    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TableColumn<User,String> firstName;

    @FXML
    private TableColumn<User,String> gender;

    @FXML
    private TableColumn<User,Integer> id;

    @FXML
    private TableColumn<User, String> lastName;

    @FXML
    private TableColumn<User, String> userName;

    @FXML
    private TableView<User> userTable;
    ObservableList<User> users = FXCollections.observableArrayList();
    UserDOA userDOA = new UserDOA();
    // populate users

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
        userName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        firstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        gender.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        try {
            users.addAll(userDOA.getAllUser());


            userTable.setItems(users);

            System.out.println(users);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}
