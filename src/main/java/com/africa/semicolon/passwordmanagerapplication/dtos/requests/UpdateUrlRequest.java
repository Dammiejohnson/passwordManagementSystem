package com.africa.semicolon.passwordmanagerapplication.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUrlRequest {
    private String userEmailAddress;
    private String userPassword;
    private String urlAddress;
    private String urlpassword;
    private String urlEmailAddress;


}
