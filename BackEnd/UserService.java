package com.example.moneymanagement.service;

import com.example.moneymanagement.model.User;
import com.example.moneymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String email, String password) {
        // You should hash the password before saving
        User newUser = new User(email, password); 
        return userRepository.save(newUser); // Save to the database
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Additional methods for updating and deleting users can be added here
}
