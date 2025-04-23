package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class User7Goal1 {
    @FXML private TextField employeeNameTextField;
    @FXML private TableColumn<EmployeeReg, String> employeeDesignationTableCol;
    @FXML private TableColumn<EmployeeReg, LocalDate> dateOfBirthTableCol;
    @FXML private TableColumn<EmployeeReg, String> addressTableCol;
    @FXML private TextField designationTextField;
    @FXML private TableColumn<EmployeeReg, String> idTableCol;
    @FXML private TextArea employeeIdTextArea;
    @FXML private TableColumn<EmployeeReg, String> employeeNameTableCol;
    @FXML private TableView<EmployeeReg> user7Goal1TableView;
    @FXML private TableColumn<EmployeeReg, String> employeeIdTableCol;
    @FXML private DatePicker dobDatePicker;
    @FXML private TextField addressTextField;

    private ObservableList<EmployeeReg> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        employeeNameTableCol.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        employeeIdTableCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employeeDesignationTableCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        dateOfBirthTableCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        addressTableCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        idTableCol.setCellValueFactory(new PropertyValueFactory<>("id"));


        loadEmployeeData();
    }

    private void loadEmployeeData() {
        try {
            employeeList = EmployeeReg.loadFromFile();
            user7Goal1TableView.setItems(employeeList);
        } catch (Exception e) {
            showAlert("Error", "Failed to load employee data", false);
            employeeList = FXCollections.observableArrayList();
            user7Goal1TableView.setItems(employeeList);
        }
    }

    @FXML
    public void addButtonOnClick(ActionEvent actionEvent) {
        if (validateInput()) {
            try {
                EmployeeReg employee = new EmployeeReg(
                        employeeNameTextField.getText(),
                        employeeIdTextArea.getText(),
                        designationTextField.getText(),
                        dobDatePicker.getValue(),
                        addressTextField.getText()
                );

                EmployeeReg.saveToFile(employee);
                employeeList.add(employee);
                clearForm();
                showAlert("Success", "Employee added successfully", true);
            } catch (Exception e) {
                showAlert("Error", "Failed to save employee: " + e.getMessage(), false);
            }
        }
    }

    private boolean validateInput() {
        if (employeeNameTextField.getText().isEmpty() ||
                employeeIdTextArea.getText().isEmpty() ||
                designationTextField.getText().isEmpty() ||
                dobDatePicker.getValue() == null ||
                addressTextField.getText().isEmpty()) {

            showAlert("Validation Error", "All fields are required", false);
            return false;
        }
        return true;
    }

    private void clearForm() {
        employeeNameTextField.clear();
        employeeIdTextArea.clear();
        designationTextField.clear();
        dobDatePicker.setValue(null);
        addressTextField.clear();
    }

    private void showAlert(String title, String message, boolean isSuccess) {
        Alert alert = new Alert(isSuccess ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}