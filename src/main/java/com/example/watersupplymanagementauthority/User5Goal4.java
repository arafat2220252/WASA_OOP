package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class User5Goal4 {
    @FXML private TextField idTextField;
    @FXML private ComboBox<String> monthComboBox;
    @FXML private TextField amountTextField;
    @FXML private DatePicker paymentDateTextField;
    @FXML private TextArea statusTextArea;
    @FXML private TableView<WaterBill> user5Goal4TableView;
    @FXML private TableColumn<WaterBill, String> idTableCol;
    @FXML private TableColumn<WaterBill, String> monthTableCol;
    @FXML private TableColumn<WaterBill, Double> amountTableCol;
    @FXML private TableColumn<WaterBill, LocalDate> dateTableCol;
    @FXML private TableColumn<WaterBill, String> statusTableCol;

    private ObservableList<WaterBill> billList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idTableCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        monthTableCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        amountTableCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateTableCol.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        statusTableCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        monthComboBox.getItems().addAll(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
    }

    @FXML
    public void submitButtonOnClick(ActionEvent actionEvent) {
        try {
            
            if (idTextField.getText().isEmpty() ||
                    monthComboBox.getValue() == null ||
                    amountTextField.getText().isEmpty() ||
                    paymentDateTextField.getValue() == null) {
                statusTextArea.setText("Error: All fields are required!");
                return;
            }

            double amount = Double.parseDouble(amountTextField.getText());

            WaterBill bill = new WaterBill(
                    idTextField.getText(),
                    monthComboBox.getValue(),
                    amount,
                    paymentDateTextField.getValue()
            );

            WaterBill.saveToFile(bill);

            billList.add(bill);
            user5Goal4TableView.setItems(billList);

            statusTextArea.setText("Payment successful!\n" +
                    "ID: " + bill.getId() + "\n" +
                    "Amount: " + bill.getAmount());

            idTextField.clear();
            monthComboBox.getSelectionModel().clearSelection();
            amountTextField.clear();
            paymentDateTextField.setValue(null);

        } catch (NumberFormatException e) {
            statusTextArea.setText("Error: Amount must be a number!");
        } catch (Exception e) {
            statusTextArea.setText("Error: " + e.getMessage());
        }
    }
}