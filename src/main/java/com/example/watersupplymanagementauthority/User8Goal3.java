package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class User8Goal3 {
    @FXML private TextField locationTextField;
    @FXML private TextField descriptionTextField;
    @FXML private DatePicker date;
    @FXML private ComboBox<String> issueTypeComboBox;
    @FXML private TableView<Issue> user8Goal3TableView;
    @FXML private TableColumn<Issue, String> locationTableCol;
    @FXML private TableColumn<Issue, String> descriptionTableCol;
    @FXML private TableColumn<Issue, LocalDate> dateTableCol;
    @FXML private TableColumn<Issue, String> issueTableCol;

    private ObservableList<Issue> issueList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        
        locationTableCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        descriptionTableCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateTableCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        issueTableCol.setCellValueFactory(new PropertyValueFactory<>("issueType"));

        
        issueTypeComboBox.getItems().addAll(
                "Water Leakage",
                "Pipe Damage",
                "Water Contamination",
                "Low Water Pressure",
                "Meter Issue",
                "Other"
        );

        
        loadIssueData();
    }

    private void loadIssueData() {
        try {
            issueList = Issue.loadFromFile();
            user8Goal3TableView.setItems(issueList);
        } catch (Exception e) {
            showAlert("Error", "Failed to load issue data", false);
            issueList = FXCollections.observableArrayList();
            user8Goal3TableView.setItems(issueList);
        }
    }

    @FXML
    public void addButtonOnClick(ActionEvent actionEvent) {
        if (validateInput()) {
            try {
                Issue issue = new Issue(
                        locationTextField.getText(),
                        descriptionTextField.getText(),
                        date.getValue(),
                        issueTypeComboBox.getValue()
                );

                Issue.saveToFile(issue);
                issueList.add(issue);
                clearForm();
                showAlert("Success", "Issue added successfully", true);
            } catch (Exception e) {
                showAlert("Error", "Failed to save issue: " + e.getMessage(), false);
            }
        }
    }

    private boolean validateInput() {
        if (locationTextField.getText().isEmpty() ||
                descriptionTextField.getText().isEmpty() ||
                date.getValue() == null ||
                issueTypeComboBox.getValue() == null) {

            showAlert("Validation Error", "All fields are required", false);
            return false;
        }
        return true;
    }

    private void clearForm() {
        locationTextField.clear();
        descriptionTextField.clear();
        date.setValue(null);
        issueTypeComboBox.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message, boolean isSuccess) {
        Alert alert = new Alert(isSuccess ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}