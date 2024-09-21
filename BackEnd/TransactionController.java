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
            @RequestParam String email,
            @RequestParam BigDecimal amount,
            @RequestParam String category,
            @RequestParam String modeOfPayment) {
        Transaction newTransaction = transactionService.createTransaction(email, amount, category, modeOfPayment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }

    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotalSpent(
            @RequestParam String email,
            @RequestParam String modeOfPayment) {
        BigDecimal totalSpent = transactionService.getTotalSpentByUserAndMode(email, modeOfPayment);
        return ResponseEntity.ok(totalSpent);
    }

    // Additional methods for getting, updating, and deleting transactions can be added here
}
