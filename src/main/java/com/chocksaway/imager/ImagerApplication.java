package com.chocksaway.imager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImagerApplication {
    private static final Logger logger = LoggerFactory.getLogger(ImagerApplication.class);

    public static void main(String[] args) {
        logger.info(">>> Starting Imager");
        SpringApplication.run(ImagerApplication.class, args);
    }

}
