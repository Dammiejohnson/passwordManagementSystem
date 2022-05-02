package com.africa.semicolon.passwordmanagerapplication.dtos.responses;

import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UserDTO;
import com.africa.semicolon.passwordmanagerapplication.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class APIResponse {
    private UserDTO payload;
    private UpdateUserResponse payloadUpdate;
    private String message;
    private boolean isSuccessful;
}
