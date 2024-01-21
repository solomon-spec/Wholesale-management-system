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


public class UserDetailController extends SceneController implements Initializable {

    @FXML
    private TableColumn<Order,String> PaymentStatus;

    @FXML
    private TableColumn<Order,String> shippingAddress;

    @FXML
    private TableColumn<Order, Float> totalAmount;

    @FXML
    private TableColumn<Order, Integer> userId;
    @FXML
    private TableColumn<Order, String> orderDate;

    @FXML
    private TableColumn<Order,Integer> orderId;

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TextField password;
    @FXML
    private TextField email;

    @FXML
    private TextField gender;

    @FXML
    private TextField lname;

    @FXML
    private TextField registrationDate;

    @FXML
    private TextField name;

    @FXML
    private TextField username;

    private ObservableList<Order> orders = FXCollections.observableArrayList();
    private int id ;

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        orderId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<Order, Integer>("orderId"));
        userId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<Order, Integer>("userId"));
        orderDate.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<Order, String>("orderDate"));
        totalAmount.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<Order, Float>("totalAmount"));
        shippingAddress.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<Order, String>("shippingAddress"));
        PaymentStatus.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<Order, String>("paymentStatus"));
        try{
            OrderDAO orderDOA = new OrderDAO();
            orders.addAll(orderDOA.getOrderByUserId(id));
            orderTable.setItems(orders);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void fillUserDetails(User user) {
        userId.setText(String.valueOf(user.getUserId()));
        username.setText(user.getUserName());
        email.setText(user.getEmail());
        name.setText(user.getFirstName());
        lname.setText(user.getLastName());
        password.setText(user.getPassword());
        gender.setText(user.getGender());
        id = user.getUserId();
    }
}
