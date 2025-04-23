package com.example.watersupplymanagementauthority;

import java.io.*;
import java.io.Serializable;

public class Salary implements Serializable {
    private String employeeId;
    private String employeeName;
    private String designation;
    private double salary;
    private double incomeTax;
    private double housingAllowance;
    private double totalSalary;

    public Salary(String employeeId, String employeeName, String designation,
                  double salary, double housingAllowance) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
        this.salary = salary;
        this.housingAllowance = housingAllowance;


        this.incomeTax = (salary > 15000) ? salary * 0.15 : 0;


        this.totalSalary = salary + housingAllowance - this.incomeTax;
    }


    public String getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public String getDesignation() { return designation; }
    public double getSalary() { return salary; }
    public double getIncomeTax() { return incomeTax; }
    public double getHousingAllowance() { return housingAllowance; }
    public double getTotalSalary() { return totalSalary; }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %.2f | %.2f | %.2f | %.2f",
                employeeId, employeeName, designation, salary,
                incomeTax, housingAllowance, totalSalary);
    }


    public static void saveToFile(Salary salary) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("salary.bin", true))) {
            oos.writeObject(salary);
        }
    }

    public static void loadFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("salary.bin"))) {
            while (true) {
                System.out.println(ois.readObject());
            }
        } catch (EOFException e) {

        }
    }
}