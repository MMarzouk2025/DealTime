package com.deal.base.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {

    private long custId;
    private String custEmail;
    private String custPassword;
    private String custFirstName;
    private String custLastName;
    private String custAddress;
    private String custJob;
    private String custMobileNumber;
    private LocalDate custDateOfBirth;
    private double custCreditLimit;
    private String custInterests;
    private Cart custCart;

    public Customer() {
        custCart = new Cart();
    }

    public Customer(String custEmail, String custPassword, String custFirstName, String custLastName, double custCreditLimit, String custMobileNumber) {
        this();
        this.custEmail = custEmail;
        this.custPassword = custPassword;
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.custCreditLimit = custCreditLimit;
        this.custMobileNumber = custMobileNumber;
    }

    public long getCustId() {
        return custId;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustJob() {
        return custJob;
    }

    public void setCustJob(String custJob) {
        this.custJob = custJob;
    }

    public LocalDate getCustDateOfBirth() {
        return custDateOfBirth;
    }

    public void setCustDateOfBirth(LocalDate custDateOfBirth) {
        this.custDateOfBirth = custDateOfBirth;
    }

    public double getCustCreditLimit() {
        return custCreditLimit;
    }

    public void setCustCreditLimit(double custCreditLimit) {
        this.custCreditLimit = custCreditLimit;
    }

    public String getCustInterests() {
        return custInterests;
    }

    public void setCustInterests(String custInterests) {
        this.custInterests = custInterests;
    }

    public Cart getCustCart() {
        return custCart;
    }

    public void setCustCart(Cart custCart) {
        this.custCart = custCart;
    }

    public String getCustMobileNumber() {
        return custMobileNumber;
    }

    public void setCustMobileNumber(String custMobileNumber) {
        this.custMobileNumber = custMobileNumber;
    }

    @Override
    public String toString() {
        return "Customer{" + "custId=" + custId + ", custEmail=" + custEmail + ", custPassword=" + custPassword + ", custFirstName=" + custFirstName + ", custLastName=" + custLastName + ", custAddress=" + custAddress + ", custJob=" + custJob + ", custDateOfBirth=" + custDateOfBirth + ", custCreditLimit=" + custCreditLimit + ", custInterests=" + custInterests + ", custCart=" + custCart + '}';
    }

}
