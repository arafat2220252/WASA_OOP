package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.time.LocalDate;

public class SalaryProcess implements Serializable {
    private String employeeId;
    private String employeeName;
    private String designation;
    private LocalDate payingDate;
    private double amount;
    private String status;

    public SalaryProcess(String employeeId, String employeeName, String designation,
                         LocalDate payingDate, double amount, String status) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
        this.payingDate = payingDate;
        this.amount = amount;
        this.status = status;
    }


    public String getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public String getDesignation() { return designation; }
    public LocalDate getPayingDate() { return payingDate; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return employeeId + " | " + employeeName + " | " + designation + " | " +
                payingDate + " | " + amount + " | " + status;
    }

    public static void saveToFile(SalaryProcess salary) throws IOException {
        File file = new File("salaryProcess.bin");
        boolean fileExists = file.exists();

        try (ObjectOutputStream oos = fileExists ?
                new AppendingObjectOutputStream(new FileOutputStream(file, true)) :
                new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(salary);
        }
    }

    public static ObservableList<SalaryProcess> loadFromFile() throws IOException, ClassNotFoundException {
        ObservableList<SalaryProcess> salaries = FXCollections.observableArrayList();
        File file = new File("salaryProcess.bin");

        if (!file.exists()) {
            return salaries; 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                salaries.add((SalaryProcess) ois.readObject());
            }
        } catch (EOFException e) {
            
        }
        return salaries;
    }


    private static class AppendingObjectOutputStream extends ObjectOutputStream {
        public AppendingObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {

        }
    }
}