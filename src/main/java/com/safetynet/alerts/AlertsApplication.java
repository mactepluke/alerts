package com.safetynet.alerts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsApplication {

    private static final Logger logger = LoggerFactory.getLogger(AlertsApplication.class);

    public static void main(String[] args) {
        logger.info("Starting 'Safetynet Alerts' Application");
        SpringApplication.run(AlertsApplication.class, args);
    }

}
