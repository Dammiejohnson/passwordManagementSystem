package com.africa.semicolon.passwordmanagerapplication.services;

import com.africa.semicolon.passwordmanagerapplication.PasswordManagerApplication;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.AddUserRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UpdateUserDTO;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UserDTO;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.UpdateUserResponse;
import com.africa.semicolon.passwordmanagerapplication.exceptions.PasswordManagerApplicationException;
import com.africa.semicolon.passwordmanagerapplication.models.Url;
import com.africa.semicolon.passwordmanagerapplication.models.User;
import com.africa.semicolon.passwordmanagerapplication.repositories.UrlRepository;
import com.africa.semicolon.passwordmanagerapplication.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private UrlRepository urlRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDTO createAccount(AddUserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findUserByEmailAddress(userRequest.getEmailAddress());
        if(optionalUser.isPresent()) throw new PasswordManagerApplicationException("User already exists");
        User user = new User();
        modelMapper.map(userRequest, user);
        userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        modelMapper.map(user, userDTO);
        return userDTO;
    }

    @Override
    public Url addUrl(String userId, Url url) {
        User user = userRepository.findUserById(userId).orElseThrow(( )-> new PasswordManagerApplicationException("user account with id does not exists"));
        Url urlAddress = urlRepository.save(url);
        Set<Url> urls =user.getUrls();
        if(urls!=null){
        urls.add(urlAddress);}
        userRepository.save(user);
        return urlAddress;
    }

    @Override
    public UserDTO findUserById(String userId) {
        User user = userRepository.findUserById(userId).orElseThrow(( )-> new PasswordManagerApplicationException("user account with id does not exists"));
        UserDTO userDTO = new UserDTO();
        modelMapper.map(user, userDTO);
        return userDTO;
    }

    @Override
    public User findUserByIdInternal(String userId) {
        User user = userRepository.findUserById(userId).orElseThrow(( )-> new PasswordManagerApplicationException("user account with id does not exists"));
        return user;
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findUserByEmailAddress(email).orElseThrow(( )-> new PasswordManagerApplicationException("user account with email does not exists"));
        userRepository.delete(user);
    }

    @Override
    public UpdateUserResponse updateUser(String id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findUserById(id).orElseThrow(() -> new PasswordManagerApplicationException("User with this id does not exist"));
        boolean isUpdate = false;

        if (!(updateUserDTO.getEmailAddress()== null ||updateUserDTO.getEmailAddress().trim().equals(""))){
            user.setEmailAddress(updateUserDTO.getEmailAddress());
            isUpdate = true;}

        if (!(updateUserDTO.getPassword()== null ||updateUserDTO.getPassword().trim().equals(""))){
            user.setPassword(updateUserDTO.getPassword());
            isUpdate = true;}

        if (!(updateUserDTO.getUsername()== null ||updateUserDTO.getUsername().trim().equals(""))){
            user.setUsername(updateUserDTO.getUsername());
            isUpdate = true;}

        if (!(updateUserDTO.getPhoneNumber()== null ||updateUserDTO.getPhoneNumber().trim().equals(""))){
            user.setPhoneNumber(updateUserDTO.getPhoneNumber());
            isUpdate = true;}

        if (isUpdate){
            userRepository.save(user);}
        UpdateUserResponse response = new UpdateUserResponse();
        modelMapper.map(updateUserDTO, response);
        return response;
    }
}
