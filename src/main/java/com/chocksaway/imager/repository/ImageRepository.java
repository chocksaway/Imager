package com.chocksaway.imager.repository;

import com.chocksaway.imager.entity.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
}
