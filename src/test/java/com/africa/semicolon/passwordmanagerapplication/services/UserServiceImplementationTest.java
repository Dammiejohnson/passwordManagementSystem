package com.africa.semicolon.passwordmanagerapplication.services;

import com.africa.semicolon.passwordmanagerapplication.PasswordManagerApplication;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.AddUserRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UpdateUserDTO;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UserDTO;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.UpdateUserResponse;
import com.africa.semicolon.passwordmanagerapplication.exceptions.PasswordManagerApplicationException;
import com.africa.semicolon.passwordmanagerapplication.models.Url;
import com.africa.semicolon.passwordmanagerapplication.models.User;
import com.africa.semicolon.passwordmanagerapplication.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class UserServiceImplementationTest {

    @Autowired
    private UserService userService;

    private AddUserRequest userRequest;

    @BeforeEach
    void setUp() {
        Set<Url> set = new HashSet<>();
        userRequest = AddUserRequest.builder()
                .username("damijay")
                .emailAddress("test2gmail.com")
                .phoneNumber("09012345678")
                .password("dami")
                .urlSet(set)
                .build();
    }

    @Test
    void createAccountTest() {
        UserDTO userDTO = userService.createAccount(userRequest);
        assertThat(userDTO.getId(), is(notNullValue()));
    }

    @Test
    void testThatCreatingAUserEmailThatExistsThrowsException(){
        userService.createAccount(userRequest);
        userRequest = AddUserRequest.builder()
                .username("damijay")
                .emailAddress("test2gmail.com")
                .phoneNumber("09012345678")
                .password("dami")
                .build();
        assertThrows(PasswordManagerApplicationException.class, () -> userService.createAccount(userRequest));
    }

    @Test
    void testThatUserCanBeFound(){
        UserDTO userDTO = userService.createAccount(userRequest);
        UserDTO foundUser = userService.findUserById(userDTO.getId());
        assertThat(userDTO.getId(), is(foundUser.getId()));
    }

    @Test
    void testThatUserCanBeUpdated(){
        UserDTO userDTO = userService.createAccount(userRequest);
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        UpdateUserResponse response = userService.updateUser(userDTO.getId(), updateUserDTO);
        User user = userService.findUserByIdInternal(userDTO.getId());
        assertThat(user.getEmailAddress(), is("test2gmail.com"));
        assertThat(updateUserDTO.getUsername(), is(response.getUsername()));

    }

    @Test
    void thatTestThrowsExceptionWhenUserIdNotFound(){
        userService.createAccount(userRequest);
        String id = "null";
        UpdateUserDTO updateUserDto = UpdateUserDTO.builder()
                .username("newUsername")
                .phoneNumber("091234567687")
                .password("newpassword")
                .emailAddress("new@gmail.com")
                .build();
        RuntimeException thrown = assertThrows(PasswordManagerApplicationException.class, () -> userService.updateUser(id, updateUserDto));
        assertThat(thrown.getMessage(), is("User with this id does not exist"));
    }

    @Test
    void testThatUserCanAddUrl(){
        UserDTO userDTO = userService.createAccount(userRequest);
        User user = userService.findUserByIdInternal(userDTO.getId());
        String urlAddress = "www.facebook.com";
        Url url = new Url(urlAddress,user.getPassword());
        Url addedUrl = userService.addUrl(userDTO.getId(), url);
        assertThat(addedUrl.getUrlId(), is(notNullValue()));
        assertThat(addedUrl.getUrlAddress(), is("www.facebook.com"));
    }

    @AfterEach
    void tearDown() {
        userService.deleteUserByEmail("test2gmail.com");
    }
}