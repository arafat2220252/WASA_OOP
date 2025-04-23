package com.example.watersupplymanagementauthority;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.time.LocalDate;

public class Issue implements Serializable {
    private String location;
    private String description;
    private LocalDate date;
    private String issueType;

    public Issue(String location, String description, LocalDate date, String issueType) {
        this.location = location;
        this.description = description;
        this.date = date;
        this.issueType = issueType;
    }

    
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }
    public String getIssueType() { return issueType; }

    @Override
    public String toString() {
        return location + " | " + description + " | " + date + " | " + issueType;
    }

    public static void saveToFile(Issue issue) throws IOException {
        File file = new File("issue.bin");
        boolean fileExists = file.exists();

        try (ObjectOutputStream oos = fileExists ?
                new AppendingObjectOutputStream(new FileOutputStream(file, true)) :
                new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(issue);
        }
    }

    public static ObservableList<Issue> loadFromFile() throws IOException, ClassNotFoundException {
        ObservableList<Issue> issues = FXCollections.observableArrayList();
        File file = new File("issue.bin");

        if (!file.exists()) {
            return issues;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                issues.add((Issue) ois.readObject());
            }
        } catch (EOFException e) {
            
        }
        return issues;
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