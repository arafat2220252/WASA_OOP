package com.example.watersupplymanagementauthority;

import java.io.*;
import java.io.Serializable;

public class Expense implements Serializable {
    private double repairingPurpose;
    private double employeePayment;
    private double needsPurchase;
    private double ongoingProjects;
    private String month;

    public Expense(double repairingPurpose, double employeePayment,
                   double needsPurchase, double ongoingProjects, String month) {
        this.repairingPurpose = repairingPurpose;
        this.employeePayment = employeePayment;
        this.needsPurchase = needsPurchase;
        this.ongoingProjects = ongoingProjects;
        this.month = month;
    }

    public double getRepairingPurpose() { return repairingPurpose; }
    public double getEmployeePayment() { return employeePayment; }
    public double getNeedsPurchase() { return needsPurchase; }
    public double getOngoingProjects() { return ongoingProjects; }
    public String getMonth() { return month; }

    @Override
    public String toString() {
        return String.format("%.2f | %.2f | %.2f | %.2f | %s",
                repairingPurpose, employeePayment, needsPurchase, ongoingProjects, month);
    }

    public static void saveToFile(Expense expense) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("expense.bin", true))) {
            oos.writeObject(expense);
        }
    }

    public static void loadFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("expense.bin"))) {
            while (true) {
                System.out.println(ois.readObject());
            }
        } catch (EOFException e) {
        }
    }
}