package com.chocksaway.imager.repository;

import com.chocksaway.imager.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
