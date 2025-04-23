package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class User8Goal4 {
    @FXML private TextField iDTextField;
    @FXML private TextField nameTextField;
    @FXML private ComboBox<String> billTypeComboBox;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private DatePicker date;
    @FXML private TableView<Payment> user8Goal4TableView;
    @FXML private TableColumn<Payment, String> iDTableCol;
    @FXML private TableColumn<Payment, String> nameTableCol;
    @FXML private TableColumn<Payment, String> billTypeTableCol;
    @FXML private TableColumn<Payment, String> statusTableCol;
    @FXML private TableColumn<Payment, LocalDate> dateTableCol;

    private ObservableList<Payment> paymentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        
        iDTableCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameTableCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        billTypeTableCol.setCellValueFactory(new PropertyValueFactory<>("billType"));
        statusTableCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateTableCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        
        billTypeComboBox.getItems().addAll(
                "Water Bill",
                "Connection Fee",
                "Late Payment",
                "Meter Charge",
                "Other"
        );

        statusComboBox.getItems().addAll(
                "Paid",
                "Pending",
                "Overdue",
                "Cancelled"
        );

        
        loadPaymentData();
    }

    private void loadPaymentData() {
        try {
            paymentList = Payment.loadFromFile();
            user8Goal4TableView.setItems(paymentList);
        } catch (Exception e) {
            showAlert("Error", "Failed to load payment data", false);
            paymentList = FXCollections.observableArrayList();
            user8Goal4TableView.setItems(paymentList);
        }
    }

    @FXML
    public void addButtonOnClick(ActionEvent actionEvent) {
        if (validateInput()) {
            try {
                Payment payment = new Payment(
                        iDTextField.getText(),
                        nameTextField.getText(),
                        billTypeComboBox.getValue(),
                        statusComboBox.getValue(),
                        date.getValue()
                );

                Payment.saveToFile(payment);
                paymentList.add(payment);
                clearForm();
                showAlert("Success", "Payment record added successfully", true);
            } catch (Exception e) {
                showAlert("Error", "Failed to save payment: " + e.getMessage(), false);
            }
        }
    }

    private boolean validateInput() {
        if (iDTextField.getText().isEmpty() ||
                nameTextField.getText().isEmpty() ||
                billTypeComboBox.getValue() == null ||
                statusComboBox.getValue() == null ||
                date.getValue() == null) {

            showAlert("Validation Error", "All fields are required", false);
            return false;
        }
        return true;
    }

    private void clearForm() {
        iDTextField.clear();
        nameTextField.clear();
        billTypeComboBox.getSelectionModel().clearSelection();
        statusComboBox.getSelectionModel().clearSelection();
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