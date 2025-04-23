package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class User6Goal4 {
    @FXML private TextField employeeIDTextField;
    @FXML private TextField employeeNameTextField;
    @FXML private TextField designationTextField;
    @FXML private TextField salaryTextField;
    @FXML private TextArea IncomeTaxTextArea;
    @FXML private TextArea housingFareTextArea;
    @FXML private Button showButtonOnClick;
    @FXML private TableView<Salary> user6Goal4TableView;
    @FXML private TableColumn<Salary, String> idTableCol;
    @FXML private TableColumn<Salary, String> nameTableCol;
    @FXML private TableColumn<Salary, String> designationTableCol;
    @FXML private TableColumn<Salary, Double> incomeTaxTableCol;
    @FXML private TableColumn<Salary, Double> housingAllowanceTableCol;
    @FXML private TableColumn<Salary, Double> totalSalaryTableCol;

    private ObservableList<Salary> salaryList = FXCollections.observableArrayList();
    private final double FIXED_HOUSING_ALLOWANCE = 5000;

    @FXML
    public void initialize() {
        idTableCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        nameTableCol.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        designationTableCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        incomeTaxTableCol.setCellValueFactory(new PropertyValueFactory<>("incomeTax"));
        housingAllowanceTableCol.setCellValueFactory(new PropertyValueFactory<>("housingAllowance"));
        totalSalaryTableCol.setCellValueFactory(new PropertyValueFactory<>("totalSalary"));

        housingFareTextArea.setText(String.valueOf(FIXED_HOUSING_ALLOWANCE));
        housingFareTextArea.setEditable(false);

        salaryTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    double salary = Double.parseDouble(newValue);
                    double tax = (salary > 15000) ? salary * 0.15 : 0;
                    IncomeTaxTextArea.setText(String.format("%.2f", tax));
                } catch (NumberFormatException e) {
                    IncomeTaxTextArea.setText("Invalid salary");
                }
            } else {
                IncomeTaxTextArea.clear();
            }
        });

        showButtonOnClick.setOnAction(event -> handleShowButton());
    }

    private void handleShowButton() {
        try {
            if (employeeIDTextField.getText().isEmpty() ||
                    employeeNameTextField.getText().isEmpty() ||
                    designationTextField.getText().isEmpty() ||
                    salaryTextField.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "All fields are required").show();
                return;
            }

            double salary = Double.parseDouble(salaryTextField.getText());

            Salary salaryRecord = new Salary(
                    employeeIDTextField.getText(),
                    employeeNameTextField.getText(),
                    designationTextField.getText(),
                    salary,
                    FIXED_HOUSING_ALLOWANCE
            );

            Salary.saveToFile(salaryRecord);

            salaryList.add(salaryRecord);
            user6Goal4TableView.setItems(salaryList);

            employeeIDTextField.clear();
            employeeNameTextField.clear();
            designationTextField.clear();
            salaryTextField.clear();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Salary must be a valid number").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}