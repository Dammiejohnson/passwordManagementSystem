package com.africa.semicolon.passwordmanagerapplication.dtos.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String userEmail;
    private String userPassword;
}
