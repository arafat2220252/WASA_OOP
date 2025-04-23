module com.example.watersupplymanagementauthority {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.watersupplymanagementauthority to javafx.fxml;
    exports com.example.watersupplymanagementauthority;
}