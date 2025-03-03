package com.mirkoabozzi.api_gateway.repositories;

import com.mirkoabozzi.api_gateway.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
