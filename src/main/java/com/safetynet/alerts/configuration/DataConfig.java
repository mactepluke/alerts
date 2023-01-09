package com.safetynet.alerts.configuration;

import com.safetynet.alerts.service.DataFileLoader;
import com.safetynet.alerts.service.IDataFileLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataConfig {

    private static final Logger logger = LogManager.getLogger(DataConfig.class);
    private static final String DATA_FILE_PATH = "src/main/resources/data.json";

    @Autowired
    IDataFileLoader dataFileLoader;


    @Bean
    public IDataFileLoader createDataFileLoader()   {

        logger.debug("Creating DataFileLoader bean to load file:" + DATA_FILE_PATH);
        return new DataFileLoader(DATA_FILE_PATH);
    }
}
