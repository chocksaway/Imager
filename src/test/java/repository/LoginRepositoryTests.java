package repository;

import com.chocksaway.imager.ImagerApplication;
import com.chocksaway.imager.domain.Login;
import com.chocksaway.imager.repository.LoginRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ImagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginRepositoryTests {

    @LocalServerPort
    private int port;

    private final LoginRepository loginRepository;

    @Autowired
    public LoginRepositoryTests(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Test
    public void given_a_login_return_the_password() {
        Login login = new Login("milesd", "password");
        loginRepository.save(login);

        List<Login> loginList = loginRepository.findLoginByName("milesd");
        Optional<String> foundLogin = loginList.stream()
                .filter(each -> each.getName().equals("milesd"))
                .findFirst()
                .map(Login::getPassword);

        assertTrue(foundLogin.isPresent());
        assertEquals("password", foundLogin.get());
    }
}