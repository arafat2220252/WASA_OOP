package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class User6Goal3 {
    @FXML private TextField repairingPurposeTextFields;
    @FXML private TextField budgetForUpcomingProjectTextField;
    @FXML private TextField repairingAndMaintainenceTextField;
    @FXML private TextField budgetForYearlyFestivalTextField;
    @FXML private ComboBox<String> yearComboBox;
    @FXML private TableView<Budget> user6Goal3TableView;
    @FXML private TableColumn<Budget, Double> repairingPurposeTableCol;
    @FXML private TableColumn<Budget, Double> upcomingProjectTableCol;
    @FXML private TableColumn<Budget, Double> repairingAndMaintainenceTableCol;
    @FXML private TableColumn<Budget, Double> yearlyFestivalTableCol;
    @FXML private TableColumn<Budget, Double> totalBudgetTablecol;
    @FXML private TableColumn<Budget, String> yearTablecol;

    private ObservableList<Budget> budgetList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        repairingPurposeTableCol.setCellValueFactory(new PropertyValueFactory<>("repairingPurpose"));
        upcomingProjectTableCol.setCellValueFactory(new PropertyValueFactory<>("upcomingProjects"));
        repairingAndMaintainenceTableCol.setCellValueFactory(new PropertyValueFactory<>("repairingAndMaintenance"));
        yearlyFestivalTableCol.setCellValueFactory(new PropertyValueFactory<>("yearlyFestivals"));
        totalBudgetTablecol.setCellValueFactory(new PropertyValueFactory<>("totalBudget"));
        yearTablecol.setCellValueFactory(new PropertyValueFactory<>("year"));

        yearComboBox.getItems().addAll(
                "2022", "2023", "2024", "2025", "2026"
        );
    }

    @FXML
    public void showDetailsButtonOnClick(ActionEvent actionEvent) {
        try {

            double repairing = Double.parseDouble(repairingPurposeTextFields.getText());
            double upcoming = Double.parseDouble(budgetForUpcomingProjectTextField.getText());
            double maintenance = Double.parseDouble(repairingAndMaintainenceTextField.getText());
            double festivals = Double.parseDouble(budgetForYearlyFestivalTextField.getText());
            String year = yearComboBox.getValue();

            if (year == null) {
                throw new Exception("Please select a year");
            }

            Budget budget = new Budget(repairing, upcoming, maintenance, festivals, year);


            Budget.saveToFile(budget);

            budgetList.add(budget);
            user6Goal3TableView.setItems(budgetList);


            repairingPurposeTextFields.clear();
            budgetForUpcomingProjectTextField.clear();
            repairingAndMaintainenceTextField.clear();
            budgetForYearlyFestivalTextField.clear();
            yearComboBox.getSelectionModel().clearSelection();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid numbers in all budget fields").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}