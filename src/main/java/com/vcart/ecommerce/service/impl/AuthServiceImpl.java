package com.vcart.ecommerce.service.impl;

import com.vcart.ecommerce.entity.User;
import com.vcart.ecommerce.payload.request.SignUpRequest;
import com.vcart.ecommerce.repository.UserRepository;
import com.vcart.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SignUpRequest registerUser(SignUpRequest signUpRequest) {
        // Check if email already exists
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        // Create new user with encoded password
        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .firstname(signUpRequest.getFirstname())
                .lastname(signUpRequest.getLastname())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        // Save the user to the database
        userRepository.save(user);

        // Set the generated ID back to the request object for response
        signUpRequest.setId(user.getId());

        return signUpRequest;
    }
}

