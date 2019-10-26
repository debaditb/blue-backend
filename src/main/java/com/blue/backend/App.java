package com.blue.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(App.class);
        SpringApplication.run(App.class, args);
        System.setProperty("user.timezone", "UTC");
        logger.info("Successfully started the blue-backend application");
    }
}
