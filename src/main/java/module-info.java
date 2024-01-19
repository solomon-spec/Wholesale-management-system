module com.example.wholesalemanagmentsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.wholesalemanagmentsystem to javafx.fxml;
    exports com.example.wholesalemanagmentsystem;
    exports com.example.wholesalemanagmentsystem.dao;
    opens com.example.wholesalemanagmentsystem.dao to javafx.fxml;
}