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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
    @FXML
    private Label total;

    @Override
    public void initialize(java.net.URL arg0, java.util.ResourceBundle arg1) {
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        ProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        QuantityInStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        detail.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        ProductDAO productDAO = new ProductDAO();
        float totalCost = 0;
        try {
            totalCost = new CartDAO().totalCost(Main.getUsername().getUserId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        total.setText("Total: " + totalCost);


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
            // if cart is empty
            if(table.getItems().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Empty Cart");
                alert.setContentText("Your cart is empty");
                alert.showAndWait();
                return;
            }
            cartDAO.checkout(Main.getUsername().getUserId(), "address 1");
            ArrayList<Product> cart = cartDAO.getUserCart(Main.getUsername().getUserId());
            setCart(cart);
            // update the total cost
            float totalCost = cartDAO.totalCost(Main.getUsername().getUserId());
            // send success message
            total.setText("Total: " + totalCost);
            // alert success
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Checkout Successful");
            alert.setContentText("Your order has been placed successfully");
            alert.showAndWait();

        }
        catch(SQLException ex){
            ex.printStackTrace();
        }

    }
    public void emptyCart(){
        // if cart empty
        if(table.getItems().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Cart");
            alert.setContentText("Your cart is empty");
            alert.showAndWait();
            return;
        }
        CartDAO cartDAO = new CartDAO();
        try {
            cartDAO.clearCart(Main.getUsername().getUserId());
            ArrayList<Product> cart = cartDAO.getUserCart(Main.getUsername().getUserId());
            setCart(cart);
            // update the total cost
            float totalCost = cartDAO.totalCost(Main.getUsername().getUserId());
            // send success message
            total.setText("Total: " + totalCost);
            // alert success
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Cart Cleared");
            alert.setContentText("Your cart has been cleared successfully");
            alert.showAndWait();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // checkout function

}
