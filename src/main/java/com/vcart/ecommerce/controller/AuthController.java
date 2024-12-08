package com.vcart.ecommerce.controller;

import com.vcart.ecommerce.jwt.JwtUtil;
import com.vcart.ecommerce.payload.request.LoginRequest;
import com.vcart.ecommerce.payload.request.SignUpRequest;
import com.vcart.ecommerce.payload.response.LoginResponse;
import com.vcart.ecommerce.payload.response.SignUpResponse;
import com.vcart.ecommerce.service.AuthService;
import com.vcart.ecommerce.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse("Login successful", jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        SignUpRequest registeredUser = authService.registerUser(signUpRequest);
        return ResponseEntity.ok(new SignUpResponse("User registered successfully!", registeredUser.getId()));
    }
}
