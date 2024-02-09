package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.Main;
import com.example.wholesalemanagementsystem.dao.OrderDAO;
import com.example.wholesalemanagementsystem.dao.UserDOA;
import com.example.wholesalemanagementsystem.models.Order;
import com.example.wholesalemanagementsystem.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserDetailController extends SceneController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button save;

    @FXML
    private Button makeAdmin;

    @FXML
    private TextField email;

    @FXML
    private TextField isAdmin;

    @FXML
    private TextField gender;

    @FXML
    private TextField lname;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Order, String> orderDate;

    @FXML
    private TableColumn<Order, Integer> orderId;

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, String> shippingAddress;

    @FXML
    private TableColumn<Order,Float> totalAmount;

    @FXML
    private TableColumn<Order,Integer> userId;
    @FXML
    private TableColumn<Order,String> PaymentStatus;

    private int id;


    public void fillUserDetails(User user) {
        username.setText(user.getUserName());
        name.setText(user.getFirstName());
        lname.setText(user.getLastName());
        email.setText(user.getEmail());
        gender.setText(user.getGender());
        password.setText(user.getPassword());
        if(!Main.getUsername().getIsAdmin() || user.getIsAdmin()){
            makeAdmin.setVisible(false);

        }
        if(user.getIsAdmin()){
            isAdmin.setText("Admin");

        }
        else{
            isAdmin.setText("User");
        }


        this.id = user.getUserId();
        fillOrderTable();
        username.setEditable(false);

    }
    public void fillOrderTable(){
        ObservableList<Order> orders = FXCollections.observableArrayList();
        OrderDAO orderDAO = new OrderDAO();
        try{
            orders.addAll(orderDAO.getOrderByUserId(id));

            orderTable.setItems(orders);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void changeField(boolean value){
        username.setEditable(value);
        name.setEditable(value);
        lname.setEditable(value);
        email.setEditable(value);
        password.setEditable(value);
        gender.setEditable(value);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderId"));
        userId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("userId"));
        orderDate.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));
        totalAmount.setCellValueFactory(new PropertyValueFactory<Order, Float>("totalAmount"));
        PaymentStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("paymentStatus"));
        shippingAddress.setCellValueFactory(new PropertyValueFactory<Order, String>("shippingAddress"));


    }

    public void makeAdmin(ActionEvent e){
        // prompt the user to enter password
        // if password is correct then make user admin
        // else show error message
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Make Admin");
        dialog.setHeaderText("Enter your password to make user admin");
        dialog.setContentText("Password:");
        String password = dialog.showAndWait().get();
        if(Main.getUsername().getPassword().equals(password)){

            try{
                if( (new UserDOA()).makeAdmin(username.getText(), password)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("User is now admin");
                    alert.showAndWait();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Something went wrong");
                    alert.showAndWait();
                }
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        else{
            // show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect password");
            alert.showAndWait();
        }

    }
}
