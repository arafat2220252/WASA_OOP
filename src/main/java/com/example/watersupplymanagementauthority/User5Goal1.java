package com.example.watersupplymanagementauthority;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.UUID;

public class User5Goal1 {
    @FXML
    private TextField addressTextField;
    @FXML
    private TextArea statusTextArea;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField CompanynameTxtField;
    @FXML
    private DatePicker EstdDatepicker;
    @FXML
    private TextField mobileNumberTextField;
    @FXML
    private ComboBox<String> companyTypeComboBox;

    @FXML
    public void initialize() {
        
        companyTypeComboBox.getItems().addAll(
                "Private Limited",
                "Public Limited",
                "Partnership",
                "Sole Proprietorship",
                "Government"
        );
    }

    @FXML
    public void registerButtonOnClick(ActionEvent actionEvent) {
        try {
            if (CompanynameTxtField.getText().isEmpty() || emailTextField.getText().isEmpty() ||
                    addressTextField.getText().isEmpty() || mobileNumberTextField.getText().isEmpty() ||
                    EstdDatepicker.getValue() == null || companyTypeComboBox.getValue() == null) {
                statusTextArea.setText("Error: All fields are required!");
                return;
            }

            
            String uniqueId = UUID.randomUUID().toString().substring(0, 8);

            Register newRegistration = new Register(
                    CompanynameTxtField.getText(),
                    emailTextField.getText(),
                    addressTextField.getText(),
                    mobileNumberTextField.getText(),
                    EstdDatepicker.getValue(),
                    companyTypeComboBox.getValue(),
                    uniqueId
            );

            Register.saveToFile(newRegistration);

            statusTextArea.setText("Registration Successful!\n" +
                    "Company ID: " + uniqueId + "\n" +
                    "Name: " + newRegistration.getName() + "\n" +
                    "Email: " + newRegistration.getEmail() + "\n" +
                    "Mobile: " + newRegistration.getMobileNumber() + "\n" +
                    "Company Type: " + newRegistration.getCompanyType());

            clearFields();

        } catch (Exception e) {
            statusTextArea.setText("Registration Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        CompanynameTxtField.clear();
        emailTextField.clear();
        addressTextField.clear();
        mobileNumberTextField.clear();
        EstdDatepicker.setValue(null);
        companyTypeComboBox.setValue(null);
    }
}