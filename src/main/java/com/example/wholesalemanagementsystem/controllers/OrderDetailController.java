package com.example.wholesalemanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderDetailController extends SceneController {


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
    private TableColumn<?, ?> productId;

    @FXML
    private TableColumn<?, ?> quantity;
    @FXML
    private TableColumn<?, ?> orderItemId;

    @FXML
    private TableColumn<?, ?> Price;
    @FXML
    private TableView<?> table;

}
