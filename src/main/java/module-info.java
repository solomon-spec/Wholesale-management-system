module com.example.wholesalemanagmentsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.wholesalemanagmentsystem to javafx.fxml;
    exports com.example.wholesalemanagmentsystem;
}