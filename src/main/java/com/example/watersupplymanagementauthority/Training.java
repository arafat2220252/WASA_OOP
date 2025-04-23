package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.time.LocalDate;

public class Training implements Serializable {
    private String sessionName;
    private String sessionAbout;
    private LocalDate date;
    private String traineeName;
    private String status;

    public Training(String sessionName, String sessionAbout,
                    LocalDate date, String traineeName, String status) {
        this.sessionName = sessionName;
        this.sessionAbout = sessionAbout;
        this.date = date;
        this.traineeName = traineeName;
        this.status = status;
    }


    public String getSessionName() { return sessionName; }
    public String getSessionAbout() { return sessionAbout; }
    public LocalDate getDate() { return date; }
    public String getTraineeName() { return traineeName; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return sessionName + " | " + sessionAbout + " | " + date + " | " +
                traineeName + " | " + status;
    }

    public static void saveToFile(Training training) throws IOException {
        File file = new File("training.bin");
        boolean fileExists = file.exists();

        try (ObjectOutputStream oos = fileExists ?
                new AppendingObjectOutputStream(new FileOutputStream(file, true)) :
                new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(training);
        }
    }

    public static ObservableList<Training> loadFromFile() throws IOException, ClassNotFoundException {
        ObservableList<Training> trainings = FXCollections.observableArrayList();
        File file = new File("training.bin");

        if (!file.exists()) {
            return trainings;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                trainings.add((Training) ois.readObject());
            }
        } catch (EOFException e) {

        }
        return trainings;
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