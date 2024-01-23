package com.example.wholesalemanagementsystem.controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminController extends SceneController{

    public void toUserManagementScene(ActionEvent e) throws IOException {
        super.UserManagementScene(e);
    }
    public void toProductManagementScene(ActionEvent e) throws IOException {
        super.ProductManagementScene(e);
    }
    public void toOrderManagementScene(ActionEvent e) throws IOException {
        super.orderManagementScene(e);
    }
}
