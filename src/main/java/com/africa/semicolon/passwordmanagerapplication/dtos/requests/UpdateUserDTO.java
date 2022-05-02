package com.africa.semicolon.passwordmanagerapplication.dtos.requests;

import com.africa.semicolon.passwordmanagerapplication.models.Url;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateUserDTO {
    private String username;
    private String emailAddress;
    private String phoneNumber;
    private String password;
}
