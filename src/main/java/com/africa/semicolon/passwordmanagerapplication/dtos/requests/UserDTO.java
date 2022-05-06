package com.africa.semicolon.passwordmanagerapplication.dtos.requests;

import com.africa.semicolon.passwordmanagerapplication.models.Url;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String emailAddress;
    private String phoneNumber;
    //private Set<Url> urlSet = new HashSet<>();
}
