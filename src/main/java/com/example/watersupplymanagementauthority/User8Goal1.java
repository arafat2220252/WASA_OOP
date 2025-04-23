package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class User8Goal1 {
    @FXML private TextField checkingPurposeTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField reasonTextField;
    @FXML private TextField approxTimeTextField;
    @FXML private DatePicker scheduleDate;
    @FXML private TableView<WaterQuality> user8Goal1TableView;
    @FXML private TableColumn<WaterQuality, String> checkingPurposeTableCol;
    @FXML private TableColumn<WaterQuality, String> locationTableCol;
    @FXML private TableColumn<WaterQuality, String> reasonTableCol;
    @FXML private TableColumn<WaterQuality, LocalDate> scheduleTableCol;
    @FXML private TableColumn<WaterQuality, String> timeTableCol;

    private ObservableList<WaterQuality> qualityList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        
        checkingPurposeTableCol.setCellValueFactory(new PropertyValueFactory<>("checkingPurpose"));
        locationTableCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        reasonTableCol.setCellValueFactory(new PropertyValueFactory<>("reason"));
        scheduleTableCol.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        timeTableCol.setCellValueFactory(new PropertyValueFactory<>("approxTime"));

        
        loadQualityData();
    }

    private void loadQualityData() {
        try {
            qualityList = WaterQuality.loadFromFile();
            user8Goal1TableView.setItems(qualityList);
        } catch (Exception e) {
            showAlert("Error", "Failed to load quality data", false);
            qualityList = FXCollections.observableArrayList();
            user8Goal1TableView.setItems(qualityList);
        }
    }

    @FXML
    public void addOnQueueButtonOnClick(ActionEvent actionEvent) {
        if (validateInput()) {
            try {
                WaterQuality quality = new WaterQuality(
                        checkingPurposeTextField.getText(),
                        locationTextField.getText(),
                        reasonTextField.getText(),
                        scheduleDate.getValue(),
                        approxTimeTextField.getText()
                );

                WaterQuality.saveToFile(quality);
                qualityList.add(quality);
                clearForm();
                showAlert("Success", "Quality check added to queue successfully", true);
            } catch (Exception e) {
                showAlert("Error", "Failed to add quality check: " + e.getMessage(), false);
            }
        }
    }

    private boolean validateInput() {
        if (checkingPurposeTextField.getText().isEmpty() ||
                locationTextField.getText().isEmpty() ||
                reasonTextField.getText().isEmpty() ||
                scheduleDate.getValue() == null ||
                approxTimeTextField.getText().isEmpty()) {

            showAlert("Validation Error", "All fields are required", false);
            return false;
        }
        return true;
    }

    private void clearForm() {
        checkingPurposeTextField.clear();
        locationTextField.clear();
        reasonTextField.clear();
        scheduleDate.setValue(null);
        approxTimeTextField.clear();
    }

    private void showAlert(String title, String message, boolean isSuccess) {
        Alert alert = new Alert(isSuccess ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}