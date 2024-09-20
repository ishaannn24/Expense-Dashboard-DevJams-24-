package com.example.moneymanagement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private LocalDateTime dateofpayment; // Date of the transaction
    private String category;  // Description of the transaction
    private String modeofpayment;//The mode in which we pay
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Reference to the user

    // Constructors
    public Transaction() {}

    public Transaction(BigDecimal amount, LocalDateTime dateofpayment, String category, User user,String modeofpayment) {
        this.amount = amount;
        this.dateofpayment = dateofpayment;
        this.category = category;
        this.user = user;
        this.modeofpayment=modeofpayment;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return dateofpayment;
    }

    public void setDate(LocalDateTime dateofpayment) {
        this.dateofpayment = dateofpayment; // Setter for date
    }

    public String getCategory() {
        return category;
    }

    public void setDescription(String category) {
        this.category = category; // Setter for description
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user; // Setter for user
    }
    public void setmodeofpayment(String modeofpayment) {
    	this.modeofpayment=modeofpayment;
    }
    public String getmodeofpayment() {
    	return modeofpayment;
    }
}
