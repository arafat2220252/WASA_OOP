package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class User7Goal4 {
    @FXML private DatePicker date;
    @FXML private TableColumn<Attendance, Integer> presenytInNightTableCol;
    @FXML private TableColumn<Attendance, Integer> earlyLeaveTableColk;
    @FXML private TableView<Attendance> user7Goal4TableView;
    @FXML private TableColumn<Attendance, Integer> absentInMorningTableCol;
    @FXML private TableColumn<Attendance, Integer> presentInMorningTableCol;
    @FXML private TextField employeeAbsentInMorningTextField;
    @FXML private TableColumn<Attendance, Integer> absentInNIghtTableCol;
    @FXML private TextField earlyLeaveTextField;
    @FXML private TableColumn<Attendance, Integer> inLeavetableCol;
    @FXML private TableColumn<Attendance, LocalDate> dateTableCol;
    @FXML private TextField absentInNightShiftTextField;
    @FXML private TextField EmployeePresentNumberInMOrningTextField;
    @FXML private TextField inLeaveTextField;
    @FXML private TextField employeePresentInNightShiftTextField;

    private ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        presentInMorningTableCol.setCellValueFactory(new PropertyValueFactory<>("presentMorning"));
        presenytInNightTableCol.setCellValueFactory(new PropertyValueFactory<>("presentNight"));
        absentInMorningTableCol.setCellValueFactory(new PropertyValueFactory<>("absentMorning"));
        absentInNIghtTableCol.setCellValueFactory(new PropertyValueFactory<>("absentNight"));
        dateTableCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        earlyLeaveTableColk.setCellValueFactory(new PropertyValueFactory<>("earlyLeave"));
        inLeavetableCol.setCellValueFactory(new PropertyValueFactory<>("inLeave"));


        loadAttendanceData();
    }

    private void loadAttendanceData() {
        try {
            attendanceList = Attendance.loadFromFile();
            user7Goal4TableView.setItems(attendanceList);
        } catch (Exception e) {
            showAlert("Error", "Failed to load attendance data", false);
            attendanceList = FXCollections.observableArrayList();
            user7Goal4TableView.setItems(attendanceList);
        }
    }

    @FXML
    public void addButtonOnClick(ActionEvent actionEvent) {
        if (validateInput()) {
            try {
                Attendance attendance = new Attendance(
                        Integer.parseInt(EmployeePresentNumberInMOrningTextField.getText()),
                        Integer.parseInt(employeePresentInNightShiftTextField.getText()),
                        Integer.parseInt(employeeAbsentInMorningTextField.getText()),
                        Integer.parseInt(absentInNightShiftTextField.getText()),
                        date.getValue(),
                        Integer.parseInt(earlyLeaveTextField.getText()),
                        Integer.parseInt(inLeaveTextField.getText())
                );

                Attendance.saveToFile(attendance);
                attendanceList.add(attendance);
                clearForm();
                showAlert("Success", "Attendance record added successfully", true);
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter valid numbers in all fields", false);
            } catch (Exception e) {
                showAlert("Error", "Failed to save attendance: " + e.getMessage(), false);
            }
        }
    }

    private boolean validateInput() {
        if (EmployeePresentNumberInMOrningTextField.getText().isEmpty() ||
                employeePresentInNightShiftTextField.getText().isEmpty() ||
                employeeAbsentInMorningTextField.getText().isEmpty() ||
                absentInNightShiftTextField.getText().isEmpty() ||
                date.getValue() == null ||
                earlyLeaveTextField.getText().isEmpty() ||
                inLeaveTextField.getText().isEmpty()) {

            showAlert("Validation Error", "All fields are required", false);
            return false;
        }

        try {
            Integer.parseInt(EmployeePresentNumberInMOrningTextField.getText());
            Integer.parseInt(employeePresentInNightShiftTextField.getText());
            Integer.parseInt(employeeAbsentInMorningTextField.getText());
            Integer.parseInt(absentInNightShiftTextField.getText());
            Integer.parseInt(earlyLeaveTextField.getText());
            Integer.parseInt(inLeaveTextField.getText());
        } catch (NumberFormatException e) {
            showAlert("Validation Error", "All numeric fields must contain valid numbers", false);
            return false;
        }

        return true;
    }

    private void clearForm() {
        EmployeePresentNumberInMOrningTextField.clear();
        employeePresentInNightShiftTextField.clear();
        employeeAbsentInMorningTextField.clear();
        absentInNightShiftTextField.clear();
        date.setValue(null);
        earlyLeaveTextField.clear();
        inLeaveTextField.clear();
    }

    private void showAlert(String title, String message, boolean isSuccess) {
        Alert alert = new Alert(isSuccess ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}