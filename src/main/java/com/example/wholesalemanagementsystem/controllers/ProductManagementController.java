package com.example.wholesalemanagementsystem.controllers;

import com.example.wholesalemanagementsystem.Main;
import com.example.wholesalemanagementsystem.dao.CartDAO;
import com.example.wholesalemanagementsystem.dao.ProductDAO;
import com.example.wholesalemanagementsystem.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.Optional;


public class ProductManagementController extends SceneController implements Initializable {


    @FXML
    private TableColumn<Product, Integer> Price;

    @FXML
    private TableColumn<Product, String> ProductName;

    @FXML
    private TableColumn<Product, Integer> QuantityInStock;

    @FXML
    private TableColumn<Product, Void> detail;

    @FXML
    private TableColumn<Product, Integer> productId;

    @FXML
    private TableView<Product> table;

    @Override
    public void initialize(java.net.URL arg0, java.util.ResourceBundle arg1) {
        productId.setCellValueFactory(new PropertyValueFactory<Product,Integer>("productId"));
        ProductName.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<Product,Integer>("price"));
        QuantityInStock.setCellValueFactory(new PropertyValueFactory<Product,Integer>("quantity"));
        try{
            ProductDAO productDAO = new ProductDAO();
            ObservableList<Product> products = FXCollections.observableArrayList();
            for(var p: productDAO.getAllProducts()){
                if(Main.getUsername().getIsAdmin() || p.getQuantity() > 0){
                    products.add(p);
                }
            }
            //products.addAll(productDAO.getAllProducts());
            table.setItems(products);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        // this is if Main.getusername().getIsAdmin() is true

        if(Main.getUsername().getIsAdmin()){
            detail.setCellFactory(new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {


                @Override
                public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                    return new TableCell<Product, Void>() {

                        private final Button btn = new Button("Details");

                        {
                            btn.setOnAction((ActionEvent event) -> {
                                Product product = getTableView().getItems().get(getIndex());
                                System.out.println("Button clicked for Product: " + product);

                                try{
                                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("productDetail.fxml"));
                                    Parent root = loader.load();
                                    ProductDetailController productDetailController = loader.getController();
                                    productDetailController.fillData(product);
                                    allScene(root, event);

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
else {
    detail.setCellFactory(new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
        @Override
        public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
            return new TableCell<Product, Void>() {
                private final Button btn = new Button("Add to Cart");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Product product = getTableView().getItems().get(getIndex());
                        System.out.println("Button clicked for Product: " + product);

                        // Open a new dialog to get the quantity
                        TextInputDialog dialog = new TextInputDialog("1");
                        dialog.setTitle("Quantity Dialog");
                        dialog.setHeaderText("Enter the quantity of the product");
                        dialog.setContentText("Quantity:");

                        Optional<String> result = dialog.showAndWait();
                        result.ifPresent(quantity -> {
                            try {
                                CartDAO cartDAO = new CartDAO();
                                boolean isAdded = cartDAO.addProductToCart(Main.getUsername().getUserId(), product.getProductId(), Integer.parseInt(quantity));
                                if (isAdded) {
                                    System.out.println("Product added to cart successfully.");
                                } else {
                                    System.out.println("Failed to add product to cart.");
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
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

}
