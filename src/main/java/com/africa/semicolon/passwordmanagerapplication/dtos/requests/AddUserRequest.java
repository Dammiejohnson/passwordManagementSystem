package com.africa.semicolon.passwordmanagerapplication.dtos.requests;

import com.africa.semicolon.passwordmanagerapplication.models.Url;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AddUserRequest {
    private String username;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    //private Set<Url> urlSet = new HashSet<>();

    public AddUserRequest() {

    }
}
