package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class User7Goal2 {
    @FXML private TextField idTextField;
    @FXML private TableColumn<SalaryProcess, String> nameTableCol;
    @FXML private TableColumn<SalaryProcess, Double> amountTableCol;
    @FXML private TableColumn<SalaryProcess, LocalDate> payingDateTableCol;
    @FXML private TextField amountTextField;
    @FXML private TableView<SalaryProcess> user7Goal2TableView;
    @FXML private TableColumn<SalaryProcess, String> designationTableCol;
    @FXML private TextField nameTextField;
    @FXML private TextField designationTextField;
    @FXML private TableColumn<SalaryProcess, String> idTableCol;
    @FXML private DatePicker datePicker;
    @FXML private TableColumn<SalaryProcess, String> statusTableCol;

    private ObservableList<SalaryProcess> salaryList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        idTableCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        nameTableCol.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        designationTableCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        payingDateTableCol.setCellValueFactory(new PropertyValueFactory<>("payingDate"));
        amountTableCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        statusTableCol.setCellValueFactory(new PropertyValueFactory<>("status"));


        loadSalaryData();
    }

    private void loadSalaryData() {
        try {
            salaryList = SalaryProcess.loadFromFile();
            user7Goal2TableView.setItems(salaryList);
        } catch (Exception e) {
            showAlert("Error", "Failed to load salary data", false);
            salaryList = FXCollections.observableArrayList();
            user7Goal2TableView.setItems(salaryList);
        }
    }

    @FXML
    public void proceedButtonOnClick(ActionEvent actionEvent) {
        if (validateInput()) {
            try {
                SalaryProcess salary = new SalaryProcess(
                        idTextField.getText(),
                        nameTextField.getText(),
                        designationTextField.getText(),
                        datePicker.getValue(),
                        Double.parseDouble(amountTextField.getText()),
                        "Processed"
                );

                SalaryProcess.saveToFile(salary);
                salaryList.add(salary);
                clearForm();
                showAlert("Success", "Salary processed successfully", true);
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid amount format", false);
            } catch (Exception e) {
                showAlert("Error", "Failed to process salary: " + e.getMessage(), false);
            }
        }
    }

    private boolean validateInput() {
        if (idTextField.getText().isEmpty() ||
                nameTextField.getText().isEmpty() ||
                designationTextField.getText().isEmpty() ||
                datePicker.getValue() == null ||
                amountTextField.getText().isEmpty()) {

            showAlert("Validation Error", "All fields are required", false);
            return false;
        }

        try {
            Double.parseDouble(amountTextField.getText());
        } catch (NumberFormatException e) {
            showAlert("Validation Error", "Amount must be a valid number", false);
            return false;
        }

        return true;
    }

    private void clearForm() {
        idTextField.clear();
        nameTextField.clear();
        designationTextField.clear();
        datePicker.setValue(null);
        amountTextField.clear();
    }

    private void showAlert(String title, String message, boolean isSuccess) {
        Alert alert = new Alert(isSuccess ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}