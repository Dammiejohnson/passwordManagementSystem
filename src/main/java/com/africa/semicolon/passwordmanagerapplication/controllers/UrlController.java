package com.africa.semicolon.passwordmanagerapplication.controllers;

import com.africa.semicolon.passwordmanagerapplication.dtos.requests.AddUrlRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.AddUserRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UpdateUrlRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.APIResponse;
import com.africa.semicolon.passwordmanagerapplication.exceptions.PasswordManagerApplicationException;
import com.africa.semicolon.passwordmanagerapplication.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/addUrl")
    public ResponseEntity<?> addUrlAccount(@RequestBody AddUrlRequest urlRequest) {
        try {
            return new ResponseEntity<>(urlService.addUrl(urlRequest), HttpStatus.OK);
        } catch (PasswordManagerApplicationException ex) {
            APIResponse response = APIResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{urlAddress}")
    public ResponseEntity<?> findbyUrl(@PathVariable String urlAddress) {
        try {
            return new ResponseEntity<>(urlService.findbyUrl(urlAddress), HttpStatus.OK);
        } catch (PasswordManagerApplicationException ex) {
            APIResponse response = APIResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{urlAddress}")
    public ResponseEntity<?> deleteUrlAddress(@PathVariable String urlAddress) {
        try {
            return new ResponseEntity<>(urlService.deleteUrl(urlAddress), HttpStatus.OK);
        } catch (PasswordManagerApplicationException ex) {
            APIResponse response = APIResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updateUrl/{email}")
    public ResponseEntity<?> updatePasswordAccount(@PathVariable String email, @RequestBody UpdateUrlRequest newRequest) {
        try {
            return new ResponseEntity<>(urlService.updateUrl(email, newRequest), HttpStatus.OK);
        } catch (PasswordManagerApplicationException ex) {
            APIResponse response = APIResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
