package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.Main;
import com.example.wholesalemanagementsystem.dao.OrderDAO;
import com.example.wholesalemanagementsystem.models.Order;
import com.example.wholesalemanagementsystem.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderManagementController extends SceneController implements Initializable {
    @FXML
    private TableColumn<Order, Integer> UserId;

    @FXML
    private TableColumn<Order,Float> amount;

    @FXML
    private TableColumn<Order, String> date;

    @FXML
    private TableColumn<Order,Void> detail;

    @FXML
    private TableColumn<Order, Integer> orderId;

    @FXML
    private TableColumn<Order, String> paymentStatus;

    @FXML
    private TableColumn<Order, String> shippingAddress;

    @FXML
    private TableView<Order> table;
    ObservableList<Order> orders = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        UserId.setCellValueFactory(new PropertyValueFactory<Order,Integer>("userId"));
        orderId.setCellValueFactory(new PropertyValueFactory<Order,Integer>("orderId"));
        amount.setCellValueFactory(new PropertyValueFactory<Order,Float>("totalAmount"));
        date.setCellValueFactory(new PropertyValueFactory<Order,String>("orderDate"));
        paymentStatus.setCellValueFactory(new PropertyValueFactory<Order,String>("orderDate"));
        shippingAddress.setCellValueFactory(new PropertyValueFactory<Order,String>("shippingAddress"));

        try {
            OrderDAO orderDAO = new OrderDAO();
            orders.addAll(orderDAO.getAllOrders());
            table.setItems(orders);
            System.out.println("here");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        detail.setCellFactory(new Callback<TableColumn<Order, Void>, TableCell<Order, Void>>() {
            @Override
            public TableCell<Order, Void> call(final TableColumn<Order, Void> param) {
                return new TableCell<Order, Void>() {

                    private final Button btn = new Button("Action");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Order order = getTableView().getItems().get(getIndex());
                            System.out.println("Button clicked for order: " + order);
                            try{
                                System.out.println("here");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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
