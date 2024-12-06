package com.chocksaway.imager.repository;

import com.chocksaway.imager.entities.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> { }

