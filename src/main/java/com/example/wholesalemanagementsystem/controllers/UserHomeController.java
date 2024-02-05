package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.Main;
import com.example.wholesalemanagementsystem.dao.CartDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class UserHomeController extends SceneController {

    @FXML
    private Button cart;

    @FXML
    private Button product;

    @FXML
    private Button profile;
    public void toProductsScene(ActionEvent e){
        try{
            super.ProductManagementScene(e);

        }
        catch(IOException ex){
            ex.printStackTrace();
        }

    }

    public void toCartScene(ActionEvent e){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("userCart.fxml"));
            Parent root = loader.load();
            UserCartController userCartController = loader.getController();
            CartDAO cartDAO = new CartDAO();
            System.out.println("what about here");
            userCartController.setCart(cartDAO.getUserCart(Main.getUsername().getUserId()));
            allScene(root, e);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
