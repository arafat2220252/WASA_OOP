package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class User8Goal2 {
    @FXML private TextField requestIdTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField plumberLeaderNameTextField;
    @FXML private TextField teamSizeTextField;
    @FXML private TextField timeTextField;
    @FXML private DatePicker date;
    @FXML private TextArea resultArea;

    private ObservableList<Supply> supplyList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        
        loadSupplyData();
    }

    private void loadSupplyData() {
        try {
            supplyList = Supply.loadFromFile();
            displayAllSupplies();
        } catch (Exception e) {
            showAlert("Error", "Failed to load supply data", false);
            supplyList = FXCollections.observableArrayList();
        }
    }

    @FXML
    public void addButtonOnClick(ActionEvent actionEvent) {
        if (validateInput()) {
            try {
                Supply supply = new Supply(
                        requestIdTextField.getText(),
                        locationTextField.getText(),
                        plumberLeaderNameTextField.getText(),
                        Integer.parseInt(teamSizeTextField.getText()),
                        date.getValue(),
                        timeTextField.getText()
                );

                Supply.saveToFile(supply);
                supplyList.add(supply);
                clearForm();
                displayAllSupplies();
                showAlert("Success", "Supply request added successfully", true);
            } catch (NumberFormatException e) {
                showAlert("Error", "Team Size must be a number", false);
            } catch (Exception e) {
                showAlert("Error", "Failed to save supply request: " + e.getMessage(), false);
            }
        }
    }

    private void displayAllSupplies() {
        resultArea.clear();
        for (Supply supply : supplyList) {
            resultArea.appendText(supply.toString());
        }
    }

    private boolean validateInput() {
        if (requestIdTextField.getText().isEmpty() ||
                locationTextField.getText().isEmpty() ||
                plumberLeaderNameTextField.getText().isEmpty() ||
                teamSizeTextField.getText().isEmpty() ||
                date.getValue() == null ||
                timeTextField.getText().isEmpty()) {

            showAlert("Validation Error", "All fields are required", false);
            return false;
        }

        try {
            Integer.parseInt(teamSizeTextField.getText());
        } catch (NumberFormatException e) {
            showAlert("Validation Error", "Team Size must be a valid number", false);
            return false;
        }

        return true;
    }

    private void clearForm() {
        requestIdTextField.clear();
        locationTextField.clear();
        plumberLeaderNameTextField.clear();
        teamSizeTextField.clear();
        timeTextField.clear();
        date.setValue(null);
    }

    private void showAlert(String title, String message, boolean isSuccess) {
        Alert alert = new Alert(isSuccess ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}