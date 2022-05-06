package com.africa.semicolon.passwordmanagerapplication.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUrlResponse {
    private String urlAddress;
    private String urlPassword;
    private String urlEmailAddress;
    private String username;

}
