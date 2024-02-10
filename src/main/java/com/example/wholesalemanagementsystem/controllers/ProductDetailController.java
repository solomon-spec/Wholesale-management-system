package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.dao.ProductDAO;
import com.example.wholesalemanagementsystem.models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        id.setEditable(false);

    }
    public void updateProduct(){
        Product product = new Product(
                        Integer.parseInt(id.getText()),
                        name.getText(),
                        description.getText(),
                        Float.parseFloat(price.getText()),
                        Integer.parseInt(quantity.getText())
                );
        ProductDAO productDAO = new ProductDAO();
        try{
            productDAO.updateProduct(product);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Product Updated");
            alert.show();
            description.setText(product.getDescription());
            id.setText(String.valueOf(product.getProductId()));
            name.setText(product.getName());
            price.setText(String.valueOf(product.getPrice()));
            quantity.setText(String.valueOf(product.getQuantity()));
            id.setEditable(false);



        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

}
