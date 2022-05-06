package com.africa.semicolon.passwordmanagerapplication.dtos.responses;

import com.africa.semicolon.passwordmanagerapplication.models.Url;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
public class AddUrlResponse {
    private String message;
    private String username;
    @DBRef
    private Set<Url> urls = new HashSet<>();

}
