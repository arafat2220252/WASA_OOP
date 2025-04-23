package com.example.watersupplymanagementauthority;

import java.io.*;
import java.time.LocalDate;

public class Register implements Serializable {
    private String name;
    private String email;
    private String address;
    private String mobileNumber;
    private LocalDate estdDate;
    private String companyType;
    private String uniqueId;

    public Register(String name, String email, String address, String mobileNumber,
                    LocalDate estdDate, String companyType, String uniqueId) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.estdDate = estdDate;
        this.companyType = companyType;
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public LocalDate getEstdDate() {
        return estdDate;
    }

    public void setEstdDate(LocalDate estdDate) {
        this.estdDate = estdDate;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return "Register{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", estdDate=" + estdDate +
                ", companyType='" + companyType + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                '}';
    }

    public static void saveToFile(Register register) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("register.bin", true))) {
            oos.writeObject(register);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("register.bin"))) {
            while (true) {
                Register register = (Register) ois.readObject();
                System.out.println(register);
            }
        } catch (EOFException e) {
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}