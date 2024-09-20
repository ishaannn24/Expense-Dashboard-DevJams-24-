package com.example.moneymanagement.service;

import com.example.moneymanagement.model.Transaction;
import com.example.moneymanagement.model.User;
import com.example.moneymanagement.repository.TransactionRepository;
import com.example.moneymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public Transaction addTransaction(String email, BigDecimal amount, String description) {
        User user = userRepository.findByEmail(email)  // Updated to find user by email
            .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setDescription(description);
        transaction.setUser(user); // Associate transaction with user

        return transactionRepository.save(transaction); // Save to the database
    }

    // Additional methods for retrieving, updating, and deleting transactions can be added here
}
