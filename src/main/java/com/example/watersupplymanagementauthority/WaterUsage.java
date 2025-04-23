package com.example.watersupplymanagementauthority;

import java.io.*;
import java.time.LocalDate;

public class WaterUsage implements Serializable {
    private String month;
    private String year;
    private LocalDate date;
    private int unit;
    private double amount;

    public WaterUsage(String month, String year, LocalDate date, int unit) {
        this.month = month;
        this.year = year;
        this.date = date;
        this.unit = unit;
        this.amount = unit * 10; 
    }

    public String getMonth() { return month; }
    public String getYear() { return year; }
    public LocalDate getDate() { return date; }
    public int getUnit() { return unit; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return month + " | " + year + " | " + date + " | " + unit + " | " + amount;
    }

    public static void saveToFile(WaterUsage usage) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("waterUsage.bin", true))) {
            oos.writeObject(usage);
        }
    }

    public static void loadFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("waterUsage.bin"))) {
            while (true) {
                System.out.println(ois.readObject());
            }
        } catch (EOFException e) {
        }
    }
}