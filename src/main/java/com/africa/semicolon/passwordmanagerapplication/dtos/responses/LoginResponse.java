package com.africa.semicolon.passwordmanagerapplication.dtos.responses;

import com.africa.semicolon.passwordmanagerapplication.models.Url;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class LoginResponse {
    private String message;
    private Set<Url> urls = new HashSet<>();
}
