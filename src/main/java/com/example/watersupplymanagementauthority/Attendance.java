package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.time.LocalDate;

public class Attendance implements Serializable {
    private int presentMorning;
    private int presentNight;
    private int absentMorning;
    private int absentNight;
    private LocalDate date;
    private int earlyLeave;
    private int inLeave;

    public Attendance(int presentMorning, int presentNight, int absentMorning,
                      int absentNight, LocalDate date, int earlyLeave, int inLeave) {
        this.presentMorning = presentMorning;
        this.presentNight = presentNight;
        this.absentMorning = absentMorning;
        this.absentNight = absentNight;
        this.date = date;
        this.earlyLeave = earlyLeave;
        this.inLeave = inLeave;
    }

    
    public int getPresentMorning() { return presentMorning; }
    public int getPresentNight() { return presentNight; }
    public int getAbsentMorning() { return absentMorning; }
    public int getAbsentNight() { return absentNight; }
    public LocalDate getDate() { return date; }
    public int getEarlyLeave() { return earlyLeave; }
    public int getInLeave() { return inLeave; }

    @Override
    public String toString() {
        return presentMorning + " | " + presentNight + " | " + absentMorning + " | " +
                absentNight + " | " + date + " | " + earlyLeave + " | " + inLeave;
    }

    public static void saveToFile(Attendance attendance) throws IOException {
        File file = new File("attendance.bin");
        boolean fileExists = file.exists();

        try (ObjectOutputStream oos = fileExists ?
                new AppendingObjectOutputStream(new FileOutputStream(file, true)) :
                new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(attendance);
        }
    }

    public static ObservableList<Attendance> loadFromFile() throws IOException, ClassNotFoundException {
        ObservableList<Attendance> attendances = FXCollections.observableArrayList();
        File file = new File("attendance.bin");

        if (!file.exists()) {
            return attendances;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                attendances.add((Attendance) ois.readObject());
            }
        } catch (EOFException e) {
            
        }
        return attendances;
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