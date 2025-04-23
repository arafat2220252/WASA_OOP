package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class User7Goal3 {
    @FXML private TextField sessionAboutTextField;
    @FXML private DatePicker date;
    @FXML private TextArea statusTextArea;
    @FXML private TableView<Training> user7Goal3;
    @FXML private TableColumn<Training, String> sessionNameTableCol;
    @FXML private TableColumn<Training, String> traineeNameTableCol;
    @FXML private TextField traineeName;
    @FXML private Button addButton;
    @FXML private TextField sessionNameTextField;
    @FXML private TableColumn<Training, String> sessionAboutTableCol;
    @FXML private TableColumn<Training, LocalDate> dateTableCol;
    @FXML private TableColumn<Training, String> statusTableCol;

    private ObservableList<Training> trainingList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        sessionNameTableCol.setCellValueFactory(new PropertyValueFactory<>("sessionName"));
        sessionAboutTableCol.setCellValueFactory(new PropertyValueFactory<>("sessionAbout"));
        dateTableCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        traineeNameTableCol.setCellValueFactory(new PropertyValueFactory<>("traineeName"));
        statusTableCol.setCellValueFactory(new PropertyValueFactory<>("status"));


        loadTrainingData();


        addButton.setOnAction(this::addButtonOnClick);
    }

    private void loadTrainingData() {
        try {
            trainingList = Training.loadFromFile();
            user7Goal3.setItems(trainingList);
        } catch (Exception e) {
            showAlert("Error", "Failed to load training data", false);
            trainingList = FXCollections.observableArrayList();
            user7Goal3.setItems(trainingList);
        }
    }

    @FXML
    private void addButtonOnClick(ActionEvent event) {
        if (validateInput()) {
            try {
                Training training = new Training(
                        sessionNameTextField.getText(),
                        sessionAboutTextField.getText(),
                        date.getValue(),
                        traineeName.getText(),
                        statusTextArea.getText()
                );

                Training.saveToFile(training);
                trainingList.add(training);
                clearForm();
                showAlert("Success", "Training session added successfully", true);
            } catch (Exception e) {
                showAlert("Error", "Failed to save training: " + e.getMessage(), false);
            }
        }
    }

    private boolean validateInput() {
        if (sessionNameTextField.getText().isEmpty() ||
                sessionAboutTextField.getText().isEmpty() ||
                date.getValue() == null ||
                traineeName.getText().isEmpty() ||
                statusTextArea.getText().isEmpty()) {

            showAlert("Validation Error", "All fields are required", false);
            return false;
        }
        return true;
    }

    private void clearForm() {
        sessionNameTextField.clear();
        sessionAboutTextField.clear();
        date.setValue(null);
        traineeName.clear();
        statusTextArea.clear();
    }

    private void showAlert(String title, String message, boolean isSuccess) {
        Alert alert = new Alert(isSuccess ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}