package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class User5Goal2 {
    @FXML private TableView<WaterConnection> user5Goal2TableView;
    @FXML private TableColumn<WaterConnection, String> propertyDetailsTableCol;
    @FXML private TableColumn<WaterConnection, String> connectionTypeTableCol;
    @FXML private TableColumn<WaterConnection, LocalDate> installationDateTableCol;
    @FXML private TableColumn<WaterConnection, String> customerNameTableCol;
    @FXML private ComboBox<String> connectionTypeComboBox;
    @FXML private DatePicker installationDateDatePicker;
    @FXML private TextField customerNameTextField;
    @FXML private TextField propertyDetailsTextField;

    private ObservableList<WaterConnection> connectionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        propertyDetailsTableCol.setCellValueFactory(new PropertyValueFactory<>("propertyDetails"));
        connectionTypeTableCol.setCellValueFactory(new PropertyValueFactory<>("connectionType"));
        installationDateTableCol.setCellValueFactory(new PropertyValueFactory<>("installationDate"));
        customerNameTableCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        connectionTypeComboBox.getItems().addAll(
                "Metered Connection",
                "Non-Metered Connection",
                "Temporary Connection",
                "Industrial Connection"
        );
    }

    @FXML
    public void submitButtonOnClick(ActionEvent actionEvent) {
        try {
            WaterConnection connection = new WaterConnection(
                    propertyDetailsTextField.getText(),
                    connectionTypeComboBox.getValue(),
                    installationDateDatePicker.getValue(),
                    customerNameTextField.getText()
            );

            WaterConnection.saveToFile(connection);

            connectionList.add(connection);
            user5Goal2TableView.setItems(connectionList);

            propertyDetailsTextField.clear();
            connectionTypeComboBox.getSelectionModel().clearSelection();
            installationDateDatePicker.setValue(null);
            customerNameTextField.clear();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}