package com.africa.semicolon.passwordmanagerapplication.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Getter
@Setter
@Document
public class Url {
    @Id
    private String urlId;
    private String urlAddress;
    private String password;

    public Url(String urlAddress, String password) {
        this.urlAddress = urlAddress;
        this.password = password;
    }


}
