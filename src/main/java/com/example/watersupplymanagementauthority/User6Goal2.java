package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class User6Goal2 {
    @FXML private TextField repairingPurposeTextField;
    @FXML private TextField employeePaymentPurposeTextField;
    @FXML private TextField needsPurchaseTextField;
    @FXML private TextField ongoingTextField;
    @FXML private ComboBox<String> monthComboBox;
    @FXML private TableView<Expense> user6Goal2TableView;
    @FXML private TableColumn<Expense, Double> repairingPurposeTableCol;
    @FXML private TableColumn<Expense, Double> employeePaymentPurposeTableCol;
    @FXML private TableColumn<Expense, Double> expenseinNeedPurposeTableCol;
    @FXML private TableColumn<Expense, Double> expenseInOngoingProjectsTableCol;
    @FXML private TableColumn<Expense, String> montTableCol;

    private ObservableList<Expense> expenseList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        repairingPurposeTableCol.setCellValueFactory(new PropertyValueFactory<>("repairingPurpose"));
        employeePaymentPurposeTableCol.setCellValueFactory(new PropertyValueFactory<>("employeePayment"));
        expenseinNeedPurposeTableCol.setCellValueFactory(new PropertyValueFactory<>("needsPurchase"));
        expenseInOngoingProjectsTableCol.setCellValueFactory(new PropertyValueFactory<>("ongoingProjects"));
        montTableCol.setCellValueFactory(new PropertyValueFactory<>("month"));

        monthComboBox.getItems().addAll(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
    }

    @FXML
    public void showDetailsButtonOnClick(ActionEvent actionEvent) {
        try {
            double repairing = Double.parseDouble(repairingPurposeTextField.getText());
            double employee = Double.parseDouble(employeePaymentPurposeTextField.getText());
            double needs = Double.parseDouble(needsPurchaseTextField.getText());
            double ongoing = Double.parseDouble(ongoingTextField.getText());
            String month = monthComboBox.getValue();

            if (month == null) {
                throw new Exception("Please select a month");
            }

            Expense expense = new Expense(repairing, employee, needs, ongoing, month);

            Expense.saveToFile(expense);

            expenseList.add(expense);
            user6Goal2TableView.setItems(expenseList);

            repairingPurposeTextField.clear();
            employeePaymentPurposeTextField.clear();
            needsPurchaseTextField.clear();
            ongoingTextField.clear();
            monthComboBox.getSelectionModel().clearSelection();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid numbers in all amount fields").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}