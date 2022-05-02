package com.africa.semicolon.passwordmanagerapplication.services;

import com.africa.semicolon.passwordmanagerapplication.exceptions.PasswordManagerApplicationException;
import com.africa.semicolon.passwordmanagerapplication.models.Url;
import com.africa.semicolon.passwordmanagerapplication.models.User;
import com.africa.semicolon.passwordmanagerapplication.repositories.UrlRepository;
import com.africa.semicolon.passwordmanagerapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImplementation implements  UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Url addUrl(String id, String urlAddress) {
        User user = userRepository.findUserById(id).orElseThrow(() -> new PasswordManagerApplicationException("User with id does not exist"));
        Url url = new Url(urlAddress, id);
        return urlRepository.save(url);
    }
}
