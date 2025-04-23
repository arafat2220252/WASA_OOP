package com.example.watersupplymanagementauthority;

import java.io.*;
import java.io.Serializable;

public class Income implements Serializable {
    private double totalBills;
    private double amountFromGovt;
    private double amountFromRevenue;
    private double amountFromFine;
    private double totalIncome;

    public Income(double totalBills, double amountFromGovt,
                  double amountFromRevenue, double amountFromFine) {
        this.totalBills = totalBills;
        this.amountFromGovt = amountFromGovt;
        this.amountFromRevenue = amountFromRevenue;
        this.amountFromFine = amountFromFine;
        this.totalIncome = totalBills + amountFromGovt + amountFromRevenue + amountFromFine;
    }

    public double getTotalBills() { return totalBills; }
    public double getAmountFromGovt() { return amountFromGovt; }
    public double getAmountFromRevenue() { return amountFromRevenue; }
    public double getAmountFromFine() { return amountFromFine; }
    public double getTotalIncome() { return totalIncome; }

    @Override
    public String toString() {
        return String.format("%.2f | %.2f | %.2f | %.2f | %.2f",
                totalBills, amountFromGovt, amountFromRevenue, amountFromFine, totalIncome);
    }

    public static void saveToFile(Income income) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("income.bin", true))) {
            oos.writeObject(income);
        }
    }

    public static void loadFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("income.bin"))) {
            while (true) {
                System.out.println(ois.readObject());
            }
        } catch (EOFException e) {
        }
    }
}