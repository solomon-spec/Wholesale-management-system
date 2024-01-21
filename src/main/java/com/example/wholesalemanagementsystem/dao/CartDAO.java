package com.example.wholesalemanagementsystem.dao;

import com.example.wholesalemanagementsystem.models.Order;
import com.example.wholesalemanagementsystem.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.wholesalemanagementsystem.dao.OrderDAO.getOrders;

public class CartDAO {
    private Connection connection;
    // get order history of a user
    public ArrayList<Order> getOrderHistory(int userId) throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM `Order` WHERE User_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(userId));
        return getOrders(statement);

    }

    // get user cart
    public ArrayList<Product> getUserCart(int userId) throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM Cart WHERE User_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(userId));
        ArrayList<Product> products = new ArrayList<>();
        var resultSet = statement.executeQuery();
        while (resultSet.next()) {
            products.add(new Product(
                    resultSet.getInt("ProductID"),
                    resultSet.getString("ProductName"),
                    resultSet.getString("description"),
                    resultSet.getFloat("price"),
                    resultSet.getInt("QuantityInStock")
            ));
        }

        return products;

    }

    // total cost of user cart
    public float totalCost(int userId) throws SQLException{
        connection = DatabaseController.connect();
        String query = "SELECT price FROM Cart WHERE User_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(userId));
        ArrayList<Product> products = new ArrayList<>();
        var resultSet = statement.executeQuery();
        float total = 0;
        while (resultSet.next()) {
            total += resultSet.getFloat("price");

        }
        return total;

    }

    // add product to cart
    public boolean addProductToCart(int userId, int productId, int quantity) throws SQLException {
        connection = DatabaseController.connect();
        String query = "INSERT INTO Cart (User_ID, ProductID, Quantity) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(userId));
        statement.setString(2, String.valueOf(productId));
        statement.setString(3, String.valueOf(quantity));
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;

    }

    // remove product from cart
    public boolean removeProductFromCart(int userId, int productId) throws SQLException {
        connection = DatabaseController.connect();
        String query = "DELETE FROM Cart WHERE User_ID = ? AND ProductID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(userId));
        statement.setString(2, String.valueOf(productId));
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;

    }

    // update product quantity in cart
    public boolean updateProductQuantityInCart(int userId, int productId, int quantity) throws SQLException {
        connection = DatabaseController.connect();
        String query = "UPDATE Cart SET Quantity = ? WHERE User_ID = ? AND ProductID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(quantity));
        statement.setString(2, String.valueOf(userId));
        statement.setString(3, String.valueOf(productId));
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;

    }

    // clear cart
    public boolean clearCart(int userId) throws SQLException {
        connection = DatabaseController.connect();
        String query = "DELETE FROM Cart WHERE User_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(userId));
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;

    }

    // checkout from cart remove from cart and add all product to order item and create order
    public boolean checkout(int userId, String shippingAddress) throws SQLException {
        connection = DatabaseController.connect();
        String query = "INSERT INTO `Order` (User_ID, OrderDate, TotalAmount, PaymentStatus, ShippingAddress) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(userId));
        statement.setString(2, String.valueOf(java.time.LocalDate.now()));
        statement.setString(3, String.valueOf(totalCost(userId)));
        statement.setString(4, "Pending");
        statement.setString(5, shippingAddress);
        int rowsInserted = statement.executeUpdate();
        int orderId = statement.getGeneratedKeys().getInt(1);
        // add all product in orderItem table
        query = "INSERT INTO OrderItem (OrderID, ProductID, Quantity, Price) VALUES (?, ?, ?, ?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(orderId));
        statement.setString(2, String.valueOf(userId));
        for (Product product: getUserCart(userId)) {
            statement.setString(3, String.valueOf(product.getProductId()));
            statement.setString(4, String.valueOf(product.getQuantity()));
            statement.setString(5, String.valueOf(product.getPrice()));
            statement.executeUpdate();
        }
        clearCart(userId);
        return rowsInserted > 0;

    }

}
