package com.africa.semicolon.passwordmanagerapplication.services;


import com.africa.semicolon.passwordmanagerapplication.dtos.requests.AddUserRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.LoginRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UpdateUserDTO;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UserDTO;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.LoginResponse;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.UpdateUserResponse;
import com.africa.semicolon.passwordmanagerapplication.models.Url;
import com.africa.semicolon.passwordmanagerapplication.models.User;

public interface UserService {
    UserDTO createAccount(AddUserRequest userRequest);
    UserDTO findUserById(String userId);
    User findUserByIdInternal(String userId);
    void deleteUserByEmail(String email);
    LoginResponse login(LoginRequest loginRequest);
    UpdateUserResponse updateUser(String id, UpdateUserDTO updateUserDTO);


}
