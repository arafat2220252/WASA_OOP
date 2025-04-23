package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class User6Goal1 {
    @FXML private TextField totalBillsTextField;
    @FXML private TextField amountFromGovtTextField;
    @FXML private TextField amountRevenuetextField;
    @FXML private TextField fineTextField;
    @FXML private TableView<Income> user6Goal1TableView;
    @FXML private TableColumn<Income, Double> totalBillTableCol;
    @FXML private TableColumn<Income, Double> amountFRomGovtTableTableCol;
    @FXML private TableColumn<Income, Double> amountFromRevenueTableCol;
    @FXML private TableColumn<Income, Double> amountFromFineTableCol;
    @FXML private TableColumn<Income, Double> totalIncomeTableCol;

    private ObservableList<Income> incomeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        totalBillTableCol.setCellValueFactory(new PropertyValueFactory<>("totalBills"));
        amountFRomGovtTableTableCol.setCellValueFactory(new PropertyValueFactory<>("amountFromGovt"));
        amountFromRevenueTableCol.setCellValueFactory(new PropertyValueFactory<>("amountFromRevenue"));
        amountFromFineTableCol.setCellValueFactory(new PropertyValueFactory<>("amountFromFine"));
        amountFromFineTableCol.setCellValueFactory(new PropertyValueFactory<>("amountFromFine"));
        totalIncomeTableCol.setCellValueFactory(new PropertyValueFactory<>("totalIncome"));
    }

    @FXML
    public void showTotalIncomeButtonOnClick(ActionEvent actionEvent) {
        try {
            
            double totalBills = Double.parseDouble(totalBillsTextField.getText());
            double govtAmount = Double.parseDouble(amountFromGovtTextField.getText());
            double revenueAmount = Double.parseDouble(amountRevenuetextField.getText());
            double fineAmount = Double.parseDouble(fineTextField.getText());

            Income income = new Income(totalBills, govtAmount, revenueAmount, fineAmount);

            Income.saveToFile(income);

            incomeList.add(income);
            user6Goal1TableView.setItems(incomeList);

            totalBillsTextField.clear();
            amountFromGovtTextField.clear();
            amountRevenuetextField.clear();
            fineTextField.clear();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid numbers in all fields").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }


}