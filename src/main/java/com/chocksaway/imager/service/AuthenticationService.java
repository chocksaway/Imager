package com.chocksaway.imager.service;

import com.chocksaway.imager.domain.Login;
import com.chocksaway.imager.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final LoginRepository loginRepository;

    public AuthenticationService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    public boolean authenticate(String name, String password) {
        return findLoginByName(name, password);
    }

    private boolean findLoginByName(String name, String password) {
        Optional<String> foundLogin= loginRepository.findLoginByName(name).stream()
                .filter(each -> each.getName().equals(name))
                .findFirst()
                .map(Login::getPassword);

        return foundLogin.isPresent() && foundLogin.get().equals(password);
    }
}
