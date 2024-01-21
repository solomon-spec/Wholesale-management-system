module com.example.wholesalemanagmentsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.wholesalemanagementsystem to javafx.fxml;
    exports com.example.wholesalemanagementsystem.controllers to javafx.fxml;
    exports com.example.wholesalemanagementsystem;
    exports com.example.wholesalemanagementsystem.dao;
    exports com.example.wholesalemanagementsystem.models;
    opens com.example.wholesalemanagementsystem.dao to javafx.fxml;
    opens com.example.wholesalemanagementsystem.controllers to javafx.fxml;
    opens com.example.wholesalemanagementsystem.models to javafx.base;
}