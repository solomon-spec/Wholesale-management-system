package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.models.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailController  extends SceneController implements Initializable {


    @FXML
    private Label totalAmount;

    @FXML
    private Label orderDate;

    @FXML
    private Label orderId;

    @FXML
    private Label paymentStatus;
    @FXML
    private Label shippingAddress;
    @FXML
    private TableColumn<?,?> productId;

    @FXML
    private TableColumn<?, ?> quantity;
    @FXML
    private TableColumn<?, ?> orderItemId;

    @FXML
    private TableColumn<?, ?> Price;
    @FXML
    private TableView<?> table;
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
