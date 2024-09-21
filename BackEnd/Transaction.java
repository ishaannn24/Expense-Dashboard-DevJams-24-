package com.example.moneymanagement.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private LocalDateTime dateofpayment;
    private String category;
    private String modeofpayment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public Transaction() {}

    public Transaction(BigDecimal amount, LocalDateTime dateofpayment, String category, User user, String modeofpayment) {
        this.amount = amount;
        this.dateofpayment = dateofpayment;
        this.category = category;
        this.user = user;
        this.modeofpayment = modeofpayment;
    }

    // Getters and Setters
//    public Long getId() {---->No need of these getters and setters as we are not really using id we are using 
                                //password
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateofpayment() {
        return dateofpayment;
    }

    public void setDateofpayment(LocalDateTime dateofpayment) {
        this.dateofpayment = dateofpayment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getModeofpayment() {
        return modeofpayment;
    }

    public void setModeofpayment(String modeofpayment) {
        this.modeofpayment = modeofpayment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
