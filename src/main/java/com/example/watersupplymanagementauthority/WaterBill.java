package com.example.watersupplymanagementauthority;

import java.io.*;
import java.time.LocalDate;

public class WaterBill implements Serializable {
    private String id;
    private String month;
    private double amount;
    private LocalDate paymentDate;
    private String status;

    public WaterBill(String id, String month, double amount, LocalDate paymentDate) {
        this.id = id;
        this.month = month;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = "Paid"; 
    }

    public String getId() { return id; }
    public String getMonth() { return month; }
    public double getAmount() { return amount; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return id + " | " + month + " | " + amount + " | " + paymentDate + " | " + status;
    }

    public static void saveToFile(WaterBill bill) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("waterbill.bin", true))) {
            oos.writeObject(bill);
        }
    }

    public static void loadFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("waterbill.bin"))) {
            while (true) {
                System.out.println(ois.readObject());
            }
        } catch (EOFException e) {
        }
    }
}