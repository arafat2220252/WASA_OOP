package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class User5Goal3 {
    @FXML private ComboBox<String> yearComboBox;
    @FXML private ComboBox<String> monthComboBox;
    @FXML private DatePicker dateDatePicker;
    @FXML private TextField unitTextField;
    @FXML private TableView<WaterUsage> user5Goal3TableView;
    @FXML private TableColumn<WaterUsage, String> monthTableCol;
    @FXML private TableColumn<WaterUsage, String> yearTableCol;
    @FXML private TableColumn<WaterUsage, LocalDate> dateTableCol;
    @FXML private TableColumn<WaterUsage, Integer> unitTableCol;
    @FXML private TableColumn<WaterUsage, Double> amountTableCol;

    private ObservableList<WaterUsage> usageList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        
        monthTableCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        yearTableCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        dateTableCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        unitTableCol.setCellValueFactory(new PropertyValueFactory<>("unit"));
        amountTableCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        
        monthComboBox.getItems().addAll(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );

        yearComboBox.getItems().addAll(
                "2022", "2023", "2024", "2025"
        );
    }

    @FXML
    public void ShowButtonOnclick(ActionEvent actionEvent) {
        try {
            if (monthComboBox.getValue() == null ||
                    yearComboBox.getValue() == null ||
                    dateDatePicker.getValue() == null ||
                    unitTextField.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "All fields are required").show();
                return;
            }

            int unit = Integer.parseInt(unitTextField.getText());

            WaterUsage usage = new WaterUsage(
                    monthComboBox.getValue(),
                    yearComboBox.getValue(),
                    dateDatePicker.getValue(),
                    unit
            );

            WaterUsage.saveToFile(usage);

            usageList.add(usage);
            user5Goal3TableView.setItems(usageList);

            monthComboBox.getSelectionModel().clearSelection();
            yearComboBox.getSelectionModel().clearSelection();
            dateDatePicker.setValue(null);
            unitTextField.clear();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Unit must be a number").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}