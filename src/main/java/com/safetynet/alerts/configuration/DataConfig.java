package com.safetynet.alerts.configuration;

import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.service.IDataFileLoader;
import com.safetynet.alerts.service.DataFileLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@Configuration
public class DataConfig {

    private static final Logger logger = LogManager.getLogger(DataConfig.class);

    @Bean
    public DataRepository dataRepository()   {

        String dataFilePath = getApplicationProperty("datafilepath");

        IDataFileLoader dataFileLoader = new DataFileLoader();
        DataRepository dataRepository = dataFileLoader.loadDataFile(dataFilePath);
        logger.debug("Loading data file: {}", dataFilePath);

        return dataRepository;
    }


    public static String getApplicationProperty(String propertyName) {

        String property = null;
        Properties properties = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("target/classes/application.properties");
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
