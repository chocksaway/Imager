package config;

import com.chocksaway.imager.service.DataLoaderService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public DataLoaderService dataLoaderService() {
        return mock(DataLoaderService.class);
    }
}
