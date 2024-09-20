package com.example.moneymanagement.security;

import com.example.moneymanagement.model.User;
import com.example.moneymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
            .password(user.getPassword()) // Make sure the password is hashed
            .roles("USER") // Assign roles as necessary
            .build();
    }
}
