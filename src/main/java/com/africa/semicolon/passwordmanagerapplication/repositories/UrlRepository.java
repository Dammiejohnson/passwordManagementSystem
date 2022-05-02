package com.africa.semicolon.passwordmanagerapplication.repositories;

import com.africa.semicolon.passwordmanagerapplication.models.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {
}
