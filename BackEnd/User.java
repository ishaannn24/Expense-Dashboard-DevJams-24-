package com.example.moneymanagement.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class User {
    @Id
    private String email; // Using email as the primary key
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    private BigDecimal upiBudget;
    private BigDecimal creditCardBudget;
    private BigDecimal debitCardBudget;
    private BigDecimal netBankingBudget;
    private BigDecimal overallBudget;

    // Constructors
    public User() {}

    public User(String email, String password, String firstName, String lastName, LocalDate dob) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public BigDecimal getUpiBudget() {
        return upiBudget;
    }

    public void setUpiBudget(BigDecimal upiBudget) {
        this.upiBudget = upiBudget;
    }

    public BigDecimal getCreditCardBudget() {
        return creditCardBudget;
    }

    public void setCreditCardBudget(BigDecimal creditCardBudget) {
        this.creditCardBudget = creditCardBudget;
    }

    public BigDecimal getDebitCardBudget() {
        return debitCardBudget;
    }

    public void setDebitCardBudget(BigDecimal debitCardBudget) {
        this.debitCardBudget = debitCardBudget;
    }

    public BigDecimal getNetBankingBudget() {
        return netBankingBudget;
    }

    public void setNetBankingBudget(BigDecimal netBankingBudget) {
        this.netBankingBudget = netBankingBudget;
    }

    public BigDecimal getOverallBudget() {
        return overallBudget;
    }

    public void setOverallBudget(BigDecimal overallBudget) {
        this.overallBudget = overallBudget;
    }
}
