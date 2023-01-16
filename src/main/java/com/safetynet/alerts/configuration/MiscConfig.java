package com.safetynet.alerts.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class MiscConfig {

    private static final Logger logger = LogManager.getLogger(MiscConfig.class);

    public static String getProp(String propertyName) {

        String property = null;
        Properties properties = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            properties.load(fis);

            property = properties.getProperty(propertyName);

        } catch (IOException e) {
            logger.error("Error while reading the application.properties file", e);
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return property;
    }

}