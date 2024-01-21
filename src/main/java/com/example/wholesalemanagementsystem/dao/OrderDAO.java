package com.example.wholesalemanagementsystem.dao;

import com.example.wholesalemanagementsystem.models.Order;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAO {
    private Connection connection;

    public boolean createOrder(Order order) throws SQLException {
        connection = DatabaseController.connect();
        String query = "INSERT INTO `Order` (User_ID, TotalAmount, PaymentStatus, ShippingAddress) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, String.valueOf(order.getUserId()));
        statement.setString(2, String.valueOf(order.getTotalAmount()));
        statement.setString(3, order.getPaymentStatus());
        statement.setString(4, order.getShippingAddress());

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;

    }

    public boolean updateOrder(Order order) throws SQLException {
        connection = DatabaseController.connect();
        String query = "UPDATE `Order` SET User_ID = ?, TotalAmount = ?, PaymentStatus = ?, ShippingAddress = ? WHERE OrderID = ?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, String.valueOf(order.getUserId()));
        statement.setString(2, String.valueOf(order.getTotalAmount()));
        statement.setString(3, order.getPaymentStatus());
        statement.setString(4, order.getShippingAddress());
        statement.setString(5, String.valueOf(order.getOrderId()));

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;

    }

    public boolean deleteOrder(int id) throws SQLException {
        connection = DatabaseController.connect();
        String query = "DELETE FROM `Order` WHERE OrderID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

    public boolean updateOrderPaymentStatus(int id, String paymentStatus) throws SQLException {
        connection = DatabaseController.connect();
        if (!paymentStatus.equals("Pending") && !paymentStatus.equals("Completed")) {
            return false;
        }

        String query = "UPDATE `Order` SET PaymentStatus = ? WHERE OrderID = ?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, paymentStatus);
        statement.setString(2, String.valueOf(id));

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

    public Order getOrderById(int id) throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM `Order` WHERE OrderID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        Order order = null;
        var resultSet = statement.executeQuery();
        if (resultSet.next()) {
            order = new Order(
                    resultSet.getInt("OrderID"),
                    resultSet.getInt("User_ID"),
                    resultSet.getString("OrderDate"),
                    resultSet.getFloat("TotalAmount"),
                    resultSet.getString("PaymentStatus"),
                    resultSet.getString("ShippingAddress")
            );
        }
        return order;

    }


    public ArrayList<Order> getAllOrders() throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM `Order`";
        PreparedStatement statement = connection.prepareStatement(query);
        return getOrders(statement);

    }

    // search order by user id
    public ArrayList<Order> searchOrderByUserId(int userId) throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM `Order` WHERE User_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(userId));
        return getOrders(statement);

    }

    // search order by payment status
    public ArrayList<Order> searchOrderByPaymentStatus(String paymentStatus) throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM `Order` WHERE PaymentStatus = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, paymentStatus);
        return getOrders(statement);

    }

    // search order by total amount range
    public ArrayList<Order> searchOrderByTotalAmountRange(float min, float max) throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM `Order` WHERE TotalAmount BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(min));
        statement.setString(2, String.valueOf(max));
        return getOrders(statement);

    }

    static ArrayList<Order> getOrders(PreparedStatement statement) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        var resultSet = statement.executeQuery();
        while (resultSet.next()) {
            orders.add(new Order(
                    resultSet.getInt("OrderID"),
                    resultSet.getInt("User_ID"),
                    resultSet.getString("OrderDate"),
                    resultSet.getFloat("TotalAmount"),
                    resultSet.getString("PaymentStatus"),
                    resultSet.getString("ShippingAddress")
            ));
        }
        return orders;
    }


}
