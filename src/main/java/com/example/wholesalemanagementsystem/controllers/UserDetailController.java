package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.dao.OrderDAO;
import com.example.wholesalemanagementsystem.models.Order;
import com.example.wholesalemanagementsystem.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TextField registrationDate;

    @FXML
    private TextField email;

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

    private User user;


    public void fillUserDetails(User user) {
        username.setText(user.getUserName());
        name.setText(user.getFirstName());
        lname.setText(user.getLastName());
        email.setText(user.getEmail());
        gender.setText(user.getGender());
        password.setText(user.getPassword());
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

        ObservableList<Order> orders = FXCollections.observableArrayList();
        OrderDAO orderDAO = new OrderDAO();
        try{
            orders.addAll(orderDAO.getOrderByUserId(user.getUserId()));
            orderTable.setItems(orders);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
