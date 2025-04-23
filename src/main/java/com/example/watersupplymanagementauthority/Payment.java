package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.time.LocalDate;

public class Payment implements Serializable {
    private String customerId;
    private String customerName;
    private String billType;
    private String status;
    private LocalDate date;

    public Payment(String customerId, String customerName, String billType,
                   String status, LocalDate date) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.billType = billType;
        this.status = status;
        this.date = date;
    }

    
    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getBillType() { return billType; }
    public String getStatus() { return status; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return customerId + " | " + customerName + " | " + billType + " | " +
                status + " | " + date;
    }

    public static void saveToFile(Payment payment) throws IOException {
        File file = new File("payment.bin");
        boolean fileExists = file.exists();

        try (ObjectOutputStream oos = fileExists ?
                new AppendingObjectOutputStream(new FileOutputStream(file, true)) :
                new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(payment);
        }
    }

    public static ObservableList<Payment> loadFromFile() throws IOException, ClassNotFoundException {
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        File file = new File("payment.bin");

        if (!file.exists()) {
            return payments;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                payments.add((Payment) ois.readObject());
            }
        } catch (EOFException e) {
            
        }
        return payments;
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