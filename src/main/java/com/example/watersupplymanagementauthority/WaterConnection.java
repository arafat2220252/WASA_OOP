package com.example.watersupplymanagementauthority;

import java.io.*;
import java.time.LocalDate;

public class WaterConnection implements Serializable {
    private String propertyDetails;
    private String connectionType;
    private LocalDate installationDate;
    private String customerName;

    public WaterConnection(String propertyDetails, String connectionType,
                           LocalDate installationDate, String customerName) {
        this.propertyDetails = propertyDetails;
        this.connectionType = connectionType;
        this.installationDate = installationDate;
        this.customerName = customerName;
    }

    public String getPropertyDetails() { return propertyDetails; }
    public String getConnectionType() { return connectionType; }
    public LocalDate getInstallationDate() { return installationDate; }
    public String getCustomerName() { return customerName; }

    @Override
    public String toString() {
        return propertyDetails + " | " + connectionType + " | " +
                installationDate + " | " + customerName;
    }

    public static void saveToFile(WaterConnection connection) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("waterconnection.bin", true))) {
            oos.writeObject(connection);
        }
    }

    public static void loadFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("waterconnection.bin"))) {
            while (true) {
                System.out.println(ois.readObject());
            }
        } catch (EOFException e) {
        }
    }
}