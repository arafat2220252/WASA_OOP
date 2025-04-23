package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.time.LocalDate;

public class WaterQuality implements Serializable {
    private String checkingPurpose;
    private String location;
    private String reason;
    private LocalDate schedule;
    private String approxTime;

    public WaterQuality(String checkingPurpose, String location,
                        String reason, LocalDate schedule, String approxTime) {
        this.checkingPurpose = checkingPurpose;
        this.location = location;
        this.reason = reason;
        this.schedule = schedule;
        this.approxTime = approxTime;
    }

    
    public String getCheckingPurpose() { return checkingPurpose; }
    public String getLocation() { return location; }
    public String getReason() { return reason; }
    public LocalDate getSchedule() { return schedule; }
    public String getApproxTime() { return approxTime; }

    @Override
    public String toString() {
        return checkingPurpose + " | " + location + " | " + reason + " | " +
                schedule + " | " + approxTime;
    }

    public static void saveToFile(WaterQuality quality) throws IOException {
        File file = new File("waterQuality.bin");
        boolean fileExists = file.exists();

        try (ObjectOutputStream oos = fileExists ?
                new AppendingObjectOutputStream(new FileOutputStream(file, true)) :
                new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(quality);
        }
    }

    public static ObservableList<WaterQuality> loadFromFile() throws IOException, ClassNotFoundException {
        ObservableList<WaterQuality> qualities = FXCollections.observableArrayList();
        File file = new File("waterQuality.bin");

        if (!file.exists()) {
            return qualities;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                qualities.add((WaterQuality) ois.readObject());
            }
        } catch (EOFException e) {
            
        }
        return qualities;
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