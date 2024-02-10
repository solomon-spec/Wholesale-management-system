package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.dao.OrderDAO;
import com.example.wholesalemanagementsystem.dao.OrderItemDOA;
import com.example.wholesalemanagementsystem.models.Order;
import com.example.wholesalemanagementsystem.models.OrderItem;
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
    private TableColumn<OrderItem, Integer> productId;

    @FXML
    private TableColumn<OrderItem, Integer> quantity;
    @FXML
    private TableColumn<OrderItem, Integer> orderItemId;

    @FXML
    private TableColumn<OrderItem, Float> Price;
    @FXML
    private TableView<OrderItem> table;
    @FXML
    private Button changeToPaid;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productId.setCellValueFactory(new PropertyValueFactory<OrderItem,Integer>("productId"));
        quantity.setCellValueFactory(new PropertyValueFactory<OrderItem,Integer>("quantity"));
        Price.setCellValueFactory(new PropertyValueFactory<OrderItem,Float>("price"));
        orderItemId.setCellValueFactory(new PropertyValueFactory<OrderItem,Integer>("id"));



    }

    public void fillOrderItemTable(Order order){

        orderDate.setText(order.getOrderDate());
        paymentStatus.setText(order.getPaymentStatus());
        shippingAddress.setText(order.getShippingAddress());
        totalAmount.setText(String.valueOf(order.getTotalAmount()));
        orderId.setText(String.valueOf(order.getOrderId()));
        ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
        OrderItemDOA orderItemDOA = new OrderItemDOA();
        try{
            orderItems.addAll(orderItemDOA.getOrderItems(order.getOrderId()));
            table.setItems(orderItems);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        if(order.getPaymentStatus().equals("Completed")){
            changeToPaid.setVisible(false);

        }



    }


    public void changePaymentStatus(ActionEvent e){
        OrderDAO orderDAO = new OrderDAO();
        int id = Integer.parseInt(orderId.getText());
        try{
            orderDAO.updateOrderPaymentStatus(id,"Completed");
            paymentStatus.setText("Completed");
            //show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Payment status updated");
            alert.showAndWait();

        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

}
