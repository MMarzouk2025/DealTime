package com.deal.base.model;

import java.time.LocalDate;

public class Admin {

    private long adminId;
    private String adminEmail;
    private String adminPassword;
    private String adminFirstName;
    private String adminLastName;
    private String adminAddress;
    private String adminJob;
    private LocalDate adminDateOfBirth;

    public Admin() {
    }

    public Admin(String adminEmail, String adminPassword, String adminFirstName, String adminLastName) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminFirstName = adminFirstName;
        this.adminLastName = adminLastName;
    }

    public long getAdminId() {
        return adminId;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminFirstName() {
        return adminFirstName;
    }

    public void setAdminFirstName(String adminFirstName) {
        this.adminFirstName = adminFirstName;
    }

    public String getAdminLastName() {
        return adminLastName;
    }

    public void setAdminLastName(String adminLastName) {
        this.adminLastName = adminLastName;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public String getAdminJob() {
        return adminJob;
    }

    public void setAdminJob(String adminJob) {
        this.adminJob = adminJob;
    }

    public LocalDate getAdminDateOfBirth() {
        return adminDateOfBirth;
    }

    public void setAdminDateOfBirth(LocalDate adminDateOfBirth) {
        this.adminDateOfBirth = adminDateOfBirth;
    }

}
