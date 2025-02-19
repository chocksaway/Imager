package com.chocksaway.imager.repository;

import com.chocksaway.imager.entities.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PhotoRepository extends MongoRepository<Photo, String> {
    Optional<Photo> findByName(String name);
}

