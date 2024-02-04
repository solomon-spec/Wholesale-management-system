package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProductDetailController extends SceneController {
    @FXML
    private TextArea description;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private TextField quantity;

    public void fillData(Product product){
        description.setText(product.getDescription());
        id.setText(String.valueOf(product.getProductId()));
        name.setText(product.getName());
        price.setText(String.valueOf(product.getPrice()));
        quantity.setText(String.valueOf(product.getQuantity()));
    }

}
