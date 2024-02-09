package com.example.wholesalemanagementsystem;

import com.example.wholesalemanagementsystem.dao.CartDAO;
import com.example.wholesalemanagementsystem.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {
    private static User user;
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Hello World!");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("Miraf Traders");

        String css = Objects.requireNonNull(this.getClass().getResource("style.css")).toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();

    }

    public static void setUsername(User user) {
        Main.user = user;
    }

    public static User getUsername(){
        return user;
    }

    public static void main(String[] args) {
         launch();
    }
}

//-------------------functionality that are already built---------------------
/*
 okay the user can log in, or register
 when user is registered it is redirected to log in page
 when a user log they are redirected to the home page
 the home page have three parts.
 the cart
    user can see the products in the cart
    they can remove all product
    they can check out the cart
products page
    the user can view and add a product to the page

 */
//-----------------------------------to be added -------------------------------
/*
cart page
    user should change the quantity or remove a product
order history page
    full order history page
user product catalog page
    user should not be allowed to add a product with a quantity that is greater than the quantity in stock

*/