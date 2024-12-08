package com.vcart.ecommerce.payload.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class LoginResponse {
    private String message;
    private String jwtToken;
}