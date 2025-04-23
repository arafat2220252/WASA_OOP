package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.time.LocalDate;

public class Supply implements Serializable {
    private String requestId;
    private String location;
    private String plumberLeaderName;
    private int teamSize;
    private LocalDate date;
    private String estimatedTime;

    public Supply(String requestId, String location, String plumberLeaderName,
                  int teamSize, LocalDate date, String estimatedTime) {
        this.requestId = requestId;
        this.location = location;
        this.plumberLeaderName = plumberLeaderName;
        this.teamSize = teamSize;
        this.date = date;
        this.estimatedTime = estimatedTime;
    }

    
    public String getRequestId() { return requestId; }
    public String getLocation() { return location; }
    public String getPlumberLeaderName() { return plumberLeaderName; }
    public int getTeamSize() { return teamSize; }
    public LocalDate getDate() { return date; }
    public String getEstimatedTime() { return estimatedTime; }

    @Override
    public String toString() {
        return "Request ID: " + requestId + "\n" +
                "Location: " + location + "\n" +
                "Plumber Leader: " + plumberLeaderName + "\n" +
                "Team Size: " + teamSize + "\n" +
                "Date: " + date + "\n" +
                "Estimated Time: " + estimatedTime + "\n\n";
    }

    public static void saveToFile(Supply supply) throws IOException {
        File file = new File("supply.bin");
        boolean fileExists = file.exists();

        try (ObjectOutputStream oos = fileExists ?
                new AppendingObjectOutputStream(new FileOutputStream(file, true)) :
                new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(supply);
        }
    }

    public static ObservableList<Supply> loadFromFile() throws IOException, ClassNotFoundException {
        ObservableList<Supply> supplies = FXCollections.observableArrayList();
        File file = new File("supply.bin");

        if (!file.exists()) {
            return supplies;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                supplies.add((Supply) ois.readObject());
            }
        } catch (EOFException e) {
            
        }
        return supplies;
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