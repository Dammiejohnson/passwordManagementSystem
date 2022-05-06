package com.africa.semicolon.passwordmanagerapplication.services;

import com.africa.semicolon.passwordmanagerapplication.dtos.requests.AddUrlRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.AddUserRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UpdateUrlRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UserDTO;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.AddUrlResponse;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.FindUrlResponse;
import com.africa.semicolon.passwordmanagerapplication.models.Url;
import com.africa.semicolon.passwordmanagerapplication.repositories.UrlRepository;
import com.africa.semicolon.passwordmanagerapplication.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
//@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class UrlServiceImplementationTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UrlService urlService;
    @Autowired
    UrlRepository urlRepository;

    private AddUrlRequest urlRequest;
    private AddUserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = AddUserRequest.builder()
                .username("damijpx")
                .emailAddress("test3@gmail.com")
                .phoneNumber("09012345678")
                .password("Damilola5$")
                .build();

        urlRequest = AddUrlRequest.builder()
                .userEmailAddress("test3@gmail.com")
                .userPassword("Damilola5$")
                .urlAddress("www.facebook.com")
                .urlpassword("dami123")
                .urlEmailAddress("damiemail@gmail.com")
                .build();
    }

    @AfterEach
    void tearDown() {
        urlRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    void testThatUrlCanBeAdded() {
        userService.createAccount(userRequest);
//        assertThat(result.getUsername(),is("damijpx"));
        AddUrlResponse urlResponse = urlService.addUrl(urlRequest);
        assertThat(urlService.count(), is(1L));
        assertThat(urlResponse.getUrls(), is(notNullValue()));
    }

    @Test
    void testThatUrlCanBeFoundByUrlAddress() {
        userService.createAccount(userRequest);
        urlService.addUrl(urlRequest);
        FindUrlResponse foundUrl = urlService.findbyUrl(urlRequest.getUrlAddress());
        assertThat(foundUrl.getUrlEmailAddress(), is("damiemail@gmail.com"));

    }

    @Test
    void testThatUrlCanBeDeletedByUrlAddrress() {
        userService.createAccount(userRequest);
        urlService.addUrl(urlRequest);
        var result = urlService.deleteUrl(urlRequest.getUrlAddress());
        //assertThat(result, is(nullValue()));
        assertThat(result.getMessage(), is("Deleted Successfully"));
        assertThat(urlService.count(), is(0L));
    }

    @Test
    void testThatUrlsCanBeUpdated() {
        userService.createAccount(userRequest);
        AddUrlRequest urlRequest2 = AddUrlRequest.builder()
                .userEmailAddress("test3@gmail.com")
                .userPassword("Damilola5$")
                .urlAddress("www.Whatsapp.com")
                .urlpassword("dami123Whatsapp")
                .urlEmailAddress("damiemail@gmail.com")
                .build();
        urlService.addUrl(urlRequest);
        urlService.addUrl(urlRequest2);
        assertThat(urlService.count(), is(2L));
        UpdateUrlRequest updateUrlRequest = new UpdateUrlRequest();
        updateUrlRequest.setUrlAddress("www.facebook.com");
        updateUrlRequest.setUrlEmailAddress("newdamiemail@gmail.com");
        updateUrlRequest.setUrlpassword("newdami123");
        urlService.updateUrl("test3@gmail.com", updateUrlRequest);
        assertThat(updateUrlRequest.getUrlEmailAddress(), is("newdamiemail@gmail.com"));
         var user = userRepository.findUserByEmailAddress("test3@gmail.com");
           Set<Url> urls = user.get().getUrls();
           Url foundUrl = null;
        for (Url url: urls) {
          if (url.getUrlAddress().equals("www.facebook.com")){
              foundUrl = url;
          }
        }
        assertNotNull(foundUrl);
        assertEquals(foundUrl.getUrlEmailAddress(), "newdamiemail@gmail.com");
    }
}