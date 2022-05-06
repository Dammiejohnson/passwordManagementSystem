package com.africa.semicolon.passwordmanagerapplication.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUrlResponse {
    private String urlPassword;
    private String urlEmailAddress;
    private String url;
    private String message;
}
