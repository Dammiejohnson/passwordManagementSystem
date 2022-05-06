package com.africa.semicolon.passwordmanagerapplication.repositories;

import com.africa.semicolon.passwordmanagerapplication.dtos.responses.DeleteUrlResponse;
import com.africa.semicolon.passwordmanagerapplication.models.Url;
import com.africa.semicolon.passwordmanagerapplication.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<Url, String> {
    Url findUserByUrlAddress(String Url);
}
