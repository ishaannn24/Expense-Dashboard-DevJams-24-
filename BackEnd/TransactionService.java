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

    // Method to create a new transaction for a user
    public Transaction createTransaction(String email, BigDecimal amount, String category, String modeOfPayment) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDateTime dateOfPayment = LocalDateTime.now(); // Set current date and time

        Transaction transaction = new Transaction(amount, dateOfPayment, category, user, modeOfPayment);
        return transactionRepository.save(transaction);
    }

    // Method to get the total spent by a user on a specific mode of payment
    public BigDecimal getTotalSpentByUserAndMode(String email, String modeOfPayment) {
        return transactionRepository.calculateTotalSpentByUserAndMode(email, modeOfPayment);
    }

    // Updated method to update user budget after a transaction based on the mode of payment
    public void updateUserBudget(String email, BigDecimal amount, String modeOfPayment) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        BigDecimal newOverallBudget = user.getOverallBudget().subtract(amount);

        switch (modeOfPayment) {
            case "UPI":
                BigDecimal newUpiBudget = user.getUpiBudget().subtract(amount);
                if (newUpiBudget.compareTo(BigDecimal.ZERO) < 0 ) {
                    throw new RuntimeException("UPI monthly limit  exceeded,Try spending carefuuly next time");
                }
                if(newOverallBudget.compareTo(BigDecimal.ZERO) < 0) {
                	throw new RuntimeException("Total Monthly limit exceeded,Try spending more carefully next time");
                }
                user.setUpiBudget(newUpiBudget);
                break;

            case "Credit Card":
                BigDecimal newCreditCardBudget = user.getCreditCardBudget().subtract(amount);
                if (newCreditCardBudget.compareTo(BigDecimal.ZERO) < 0 ) {
                    throw new RuntimeException("Credit Card monthly limit  exceeded,Try spending carefuuly next time");
                }
                if(newOverallBudget.compareTo(BigDecimal.ZERO) < 0) {
                	throw new RuntimeException("Total Monthly limit exceeded,Try spending more carefully next time");
                }
                user.setCreditCardBudget(newCreditCardBudget);
                break;

            case "Debit Card":
                BigDecimal newDebitCardBudget = user.getDebitCardBudget().subtract(amount);
                if (newDebitCardBudget.compareTo(BigDecimal.ZERO) < 0 ) {
                    throw new RuntimeException("Debit Card monthly limit  exceeded,Try spending carefuuly next time");
                }
                if(newOverallBudget.compareTo(BigDecimal.ZERO) < 0) {
                	throw new RuntimeException("Total Monthly limit exceeded,Try spending more carefully next time");
                }
                user.setDebitCardBudget(newDebitCardBudget);
                break;

            case "NetBanking":
                BigDecimal newNetBankingBudget = user.getNetBankingBudget().subtract(amount);
                if (newNetBankingBudget.compareTo(BigDecimal.ZERO) < 0 ) {
                    throw new RuntimeException("Net Banking monthly limit  exceeded,Try spending carefuuly next time");
                }
                if(newOverallBudget.compareTo(BigDecimal.ZERO) < 0) {
                	throw new RuntimeException("Total Monthly limit exceeded,Try spending more carefully next time");
                }
                user.setNetBankingBudget(newNetBankingBudget);
                break;

            default:
                throw new RuntimeException("Invalid payment mode");
        }

        // Finally, update the overall budget after updating the individual budget
        user.setOverallBudget(newOverallBudget);
        userRepository.save(user);
    }
}
