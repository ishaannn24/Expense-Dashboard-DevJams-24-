package com.example.moneymanagement.controller;

import com.example.moneymanagement.model.Transaction;
import com.example.moneymanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(
            @RequestParam Long userId,
            @RequestParam BigDecimal amount,
            @RequestParam String description) {
        Transaction newTransaction = transactionService.addTransaction(userId, amount, description);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }

    // Additional methods for getting, updating, and deleting transactions can be added here
}
