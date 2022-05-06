package com.africa.semicolon.passwordmanagerapplication.dtos.requests;

import com.africa.semicolon.passwordmanagerapplication.models.Url;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
public class AddUrlRequest {
    private String userEmailAddress;
    private String userPassword;
    private String urlAddress;
    private String urlpassword;
    private String urlEmailAddress;
}
