package com.chocksaway.imager;

import com.chocksaway.imager.service.DataLoaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImagerApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ImagerApplication.class);

    private final DataLoaderService dataLoaderService;

    public ImagerApplication(DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }

    public static void main(String[] args) {
        logger.info(">>> Starting Imager");
        SpringApplication.run(ImagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dataLoaderService.loadInitialData();
    }
}
