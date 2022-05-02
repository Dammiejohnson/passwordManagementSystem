package com.africa.semicolon.passwordmanagerapplication.dtos.responses;

import com.africa.semicolon.passwordmanagerapplication.models.Url;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserResponse {
    private String username;
    private String emailAddress;
    private String phoneNumber;
    private Set<Url> urlSet = new HashSet<>();
}
