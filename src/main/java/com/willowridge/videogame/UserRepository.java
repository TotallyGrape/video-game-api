package com.willowridge.videogame;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    //querys the db for a specific user
    Optional<User> findByUsername(String username);
}
