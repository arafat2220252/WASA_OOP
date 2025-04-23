package com.example.watersupplymanagementauthority;

import java.io.*;
import java.io.Serializable;

public class Budget implements Serializable {
    private double repairingPurpose;
    private double upcomingProjects;
    private double repairingAndMaintenance;
    private double yearlyFestivals;
    private String year;
    private double totalBudget;

    public Budget(double repairingPurpose, double upcomingProjects,
                  double repairingAndMaintenance, double yearlyFestivals, String year) {
        this.repairingPurpose = repairingPurpose;
        this.upcomingProjects = upcomingProjects;
        this.repairingAndMaintenance = repairingAndMaintenance;
        this.yearlyFestivals = yearlyFestivals;
        this.year = year;
        this.totalBudget = repairingPurpose + upcomingProjects + repairingAndMaintenance + yearlyFestivals;
    }


    public double getRepairingPurpose() { return repairingPurpose; }
    public double getUpcomingProjects() { return upcomingProjects; }
    public double getRepairingAndMaintenance() { return repairingAndMaintenance; }
    public double getYearlyFestivals() { return yearlyFestivals; }
    public String getYear() { return year; }
    public double getTotalBudget() { return totalBudget; }

    @Override
    public String toString() {
        return String.format("%.2f | %.2f | %.2f | %.2f | %.2f | %s",
                repairingPurpose, upcomingProjects, repairingAndMaintenance, yearlyFestivals, totalBudget, year);
    }


    public static void saveToFile(Budget budget) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("budget.bin", true))) {
            oos.writeObject(budget);
        }
    }

    public static void loadFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("budget.bin"))) {
            while (true) {
                System.out.println(ois.readObject());
            }
        } catch (EOFException e) {

        }
    }
}