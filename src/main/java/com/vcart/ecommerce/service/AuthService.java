package com.vcart.ecommerce.service;

import com.vcart.ecommerce.payload.request.SignUpRequest;

public interface AuthService {
    SignUpRequest registerUser(SignUpRequest signUpRequest);
}