package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.dao.UserDOA;
import com.example.wholesalemanagementsystem.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
    @FXML
    private TableColumn<User, Void> details;
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
        details.setCellFactory(new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                return new TableCell<User, Void>() {

                    private final Button btn = new Button("Action");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            User user = getTableView().getItems().get(getIndex());
                            System.out.println("Button clicked for user: " + user);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        });


    }


}
