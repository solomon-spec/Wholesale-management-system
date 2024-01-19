package com.example.wholesalemanagmentsystem.models;

import java.util.ArrayList;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private boolean isAdmin;
    private ArrayList<Product> cart;


    public User(int userId, String userName, String password, String email, String firstName, String lastName, String gender, boolean isAdmin, ArrayList<Product> cart) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.isAdmin = isAdmin;
        this.cart = cart;
    }

    public int getUserId() {
        return userId;
    }
    public String getEmail() {
        return email;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName= lastName;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }
}
