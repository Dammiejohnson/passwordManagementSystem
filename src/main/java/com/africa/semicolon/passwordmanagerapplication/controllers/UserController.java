package com.africa.semicolon.passwordmanagerapplication.controllers;

import com.africa.semicolon.passwordmanagerapplication.dtos.requests.AddUserRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.LoginRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UpdateUserDTO;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.APIResponse;
import com.africa.semicolon.passwordmanagerapplication.exceptions.PasswordManagerApplicationException;
import com.africa.semicolon.passwordmanagerapplication.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //    public UserController(UserService userService) {
//        this.userService = userService;
//    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public ResponseEntity<?> createNewUserAccount(@RequestBody AddUserRequest userRequest) {

        try {
            APIResponse response = APIResponse.builder()
                    .payload(userService.createAccount(userRequest))
                    .message("User created successfully")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (PasswordManagerApplicationException ex) {
            APIResponse response = APIResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/id")
    @ResponseBody
    public ResponseEntity<?> findUser(@RequestParam String id) {
        try {
            APIResponse response = APIResponse.builder()
                    .payload(userService.findUserById(id))
                    .message("User found")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (PasswordManagerApplicationException ex) {
            APIResponse response = APIResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest login) {
        try {
            return new ResponseEntity<>(userService.login(login), HttpStatus.OK);
        } catch (PasswordManagerApplicationException ex) {
            APIResponse response = APIResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/updateAccount")
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestParam String id, @RequestBody UpdateUserDTO updateUserDTO) {
        try {
            APIResponse response = APIResponse.builder()
                    .payloadUpdate(userService.updateUser(id, updateUserDTO))
                    .message("Updated Successfully")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (PasswordManagerApplicationException ex) {
            APIResponse response = APIResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/delete/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email) {
        try {
            userService.deleteUserByEmail(email);
            log.info("hit endpoint!!!");
            APIResponse response = APIResponse.builder()
                    .message("User deleted successfully")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (PasswordManagerApplicationException ex) {
            APIResponse response = APIResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            log.info("hit here");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}