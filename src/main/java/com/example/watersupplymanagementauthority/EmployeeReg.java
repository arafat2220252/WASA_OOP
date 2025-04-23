package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.time.LocalDate;
import java.util.UUID;

public class EmployeeReg implements Serializable {
    private String id;
    private String employeeName;
    private String employeeId;
    private String designation;
    private LocalDate dateOfBirth;
    private String address;

    public EmployeeReg(String employeeName, String employeeId, String designation,
                       LocalDate dateOfBirth, String address) {
        this.id = UUID.randomUUID().toString();
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.designation = designation;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }


    public String getId() { return id; }
    public String getEmployeeName() { return employeeName; }
    public String getEmployeeId() { return employeeId; }
    public String getDesignation() { return designation; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return employeeName + " | " + employeeId + " | " + designation + " | " +
                dateOfBirth + " | " + address;
    }

    public static void saveToFile(EmployeeReg employee) throws IOException {
        File file = new File("employeeReg.bin");
        boolean fileExists = file.exists();

        try (ObjectOutputStream oos = fileExists ?
                new AppendingObjectOutputStream(new FileOutputStream(file, true)) :
                new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(employee);
        }
    }

    public static ObservableList<EmployeeReg> loadFromFile() throws IOException, ClassNotFoundException {
        ObservableList<EmployeeReg> employees = FXCollections.observableArrayList();
        File file = new File("employeeReg.bin");

        if (!file.exists()) {
            return employees;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                employees.add((EmployeeReg) ois.readObject());
            }
        } catch (EOFException e) {

        }
        return employees;
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