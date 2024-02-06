package com.example.wholesalemanagementsystem.dao;

import com.example.wholesalemanagementsystem.models.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemDOA {
    Connection connection;
    public ArrayList<OrderItem> getOrderItems(int orderId) throws SQLException {
        connection = DatabaseController.connect();
        String query = "SELECT * FROM javawms.orderitem WHERE OrderID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(orderId));
        ResultSet resultSet = statement.executeQuery();
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        while (resultSet.next()) {
            orderItems.add(new OrderItem(
                    resultSet.getInt("OrderItemID"),
                    resultSet.getInt("OrderID"),
                    resultSet.getInt("ProductID"),
                    resultSet.getInt("Quantity"),
                    resultSet.getFloat("Price")
            ));
        }
        return orderItems;
    }
}
