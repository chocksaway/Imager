package com.chocksaway.imager.repository;

import com.chocksaway.imager.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> { }

