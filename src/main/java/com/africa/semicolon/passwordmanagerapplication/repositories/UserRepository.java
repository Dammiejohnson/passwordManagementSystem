package com.africa.semicolon.passwordmanagerapplication.repositories;

import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UserDTO;
import com.africa.semicolon.passwordmanagerapplication.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEmailAddress(String emailAddress);
    Optional<User> findUserById(String id);
}
