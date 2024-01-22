package com.example.wholesalemanagementsystem.dao;

import com.example.wholesalemanagementsystem.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    private Connection connection;

    public Product getProductById(int id) throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM product WHERE productId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Product(
                    resultSet.getInt("ProductName"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getFloat("price"),
                    resultSet.getInt("QuantityInStock")
            );
        }

        return null;
    }
    public ArrayList<Product> getAllProducts() throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM product";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(new Product(
                    resultSet.getInt("ProductId"),
                    resultSet.getString("ProductName"),
                    resultSet.getString("description"),
                    resultSet.getFloat("price"),
                    resultSet.getInt("QuantityInStock")
            ));
        }
        return products;
    }

    // search product by name
    public ArrayList<Product> searchProductByName(String name) throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM product WHERE ProductName LIKE ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(new Product(
                    resultSet.getInt("ProductId"),
                    resultSet.getString("ProductName"),
                    resultSet.getString("description"),
                    resultSet.getFloat("price"),
                    resultSet.getInt("QuantityInStock")
            ));
        }
        return products;
    }

    // create product
    public boolean createProduct(Product product) throws SQLException {
        connection = DatabaseController.connect();
        String query = "INSERT INTO product (ProductName, description, price, QuantityInStock) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setFloat(3, product.getPrice());
        statement.setInt(4, product.getQuantity());

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

    // update product
    public boolean updateProduct(Product product) throws SQLException {
        connection = DatabaseController.connect();
        String query = "UPDATE product SET ProductName = ?, description = ?, price = ?, QuantityInStock = ? WHERE productId = ?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setFloat(3, product.getPrice());
        statement.setInt(4, product.getQuantity());
        statement.setInt(5, product.getProductId());

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

    // delete product
    public boolean deleteProduct(int id) throws SQLException {
        connection = DatabaseController.connect();
        String query = "DELETE FROM product WHERE productId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

    // update product quantity
    public boolean updateProductQuantity(int id, int quantity) throws SQLException {
        connection = DatabaseController.connect();
        String query = "UPDATE product SET QuantityInStock = ? WHERE productId = ?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, quantity);
        statement.setInt(2, id);

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

}
