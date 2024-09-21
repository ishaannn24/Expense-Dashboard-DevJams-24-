package com.example.moneymanagement.controller;

import com.example.moneymanagement.model.User;
import com.example.moneymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob) {
        
        User newUser = userService.createUser(email, password, firstName, lastName, dob);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    // Removed the getUserById method since users are now identified by email

    // Additional methods for updating and deleting users can be added here
}
