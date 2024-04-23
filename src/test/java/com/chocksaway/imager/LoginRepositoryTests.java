package com.chocksaway.imager;

import com.chocksaway.imager.domain.Login;
import com.chocksaway.imager.repository.LoginRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LoginRepositoryTests {
    @Autowired
    private LoginRepository loginRepository;

    @Test
    public void given_a_login_return_the_password() {
        Login login = new Login("milesd", "password");
        loginRepository.save(login);

        List<Login> loginList = loginRepository.findLoginByName("milesd");
        Optional<String> foundLogin= loginList.stream()
                .filter(each -> each.getName().equals("milesd"))
                        .findFirst()
                        .map(Login::getPassword);

        assertTrue(foundLogin.isPresent());

        assertEquals("password", foundLogin.get());
    }
}
