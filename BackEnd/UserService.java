package com.example.moneymanagement.service;

import com.example.moneymanagement.model.User;
import com.example.moneymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String email, String password, String firstName, String lastName, LocalDate dob) {
        if (!isValidPassword(password)) {
            throw new RuntimeException("Enter a stronger password for security purposes");
        }

        User newUser = new User(email, password, firstName, lastName, dob);
        return userRepository.save(newUser);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserById(String email) {
        return userRepository.findById(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void setBudget(String email, BigDecimal upiBudget, BigDecimal creditCardBudget,
                          BigDecimal debitCardBudget, BigDecimal netBankingBudget, BigDecimal overallBudget) {
        User user = getUserByEmail(email);
        
        user.setUpiBudget(upiBudget);
        user.setCreditCardBudget(creditCardBudget);
        user.setDebitCardBudget(debitCardBudget);
        user.setNetBankingBudget(netBankingBudget);
        user.setOverallBudget(overallBudget);

        userRepository.save(user);
    }

    private boolean isValidPassword(String password) {
        return Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*()_+=<>?]).+$").matcher(password).find();
    }
}
