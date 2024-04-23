package com.chocksaway.imager.repository;

import com.chocksaway.imager.domain.Login;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends ListCrudRepository<Login, Long> {
    List<Login> findLoginByName(String name);
}
