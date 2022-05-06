package com.africa.semicolon.passwordmanagerapplication.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private boolean isLoggedin;
    @DBRef
    private Set<Url> urls = new HashSet<>();


}
