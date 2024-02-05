package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.Main;
import com.example.wholesalemanagementsystem.dao.CartDAO;
import com.example.wholesalemanagementsystem.dao.ProductDAO;
import com.example.wholesalemanagementsystem.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserCartController extends SceneController implements Initializable {
    @FXML
    private TableColumn<Product, Integer> Price;

    @FXML
    private TableColumn<Product, String> ProductName;

    @FXML
    private TableColumn<Product, Integer> QuantityInStock;

    @FXML
    private TableColumn<Product, String> detail;

    @FXML
    private TableColumn<Product, Integer> productId;

    @FXML
    private TableView<Product> table;

    @Override
    public void initialize(java.net.URL arg0, java.util.ResourceBundle arg1) {
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        ProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        QuantityInStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        detail.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));

    }

    public void setCart(ArrayList<Product> cart) {

        ObservableList<Product> products = FXCollections.observableArrayList();
        System.out.println("we are here");
        products.addAll(cart);
        System.out.println(products.size());
        table.setItems(products);
    }

    public void checkout(ActionEvent e){
        CartDAO cartDAO = new CartDAO();
        try{
            cartDAO.checkout(Main.getUsername().getUserId(), "adress 1");
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }

    }
}
