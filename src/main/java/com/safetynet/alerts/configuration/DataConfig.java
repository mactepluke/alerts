package com.safetynet.alerts.configuration;

import com.safetynet.alerts.dao.*;
import com.safetynet.alerts.service.DataFileLoader;
import com.safetynet.alerts.service.IDataLists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataConfig {

    private static final Logger logger = LogManager.getLogger(DataConfig.class);
    private static final String DATA_FILE_PATH = "src/main/resources/data.json";

    @Bean
    public DataFileLoader createDataFileLoader(IPersonDAO personDAO, IMedicalRecordDAO medicalRecordDAO, IFirestationDAO firestationDAO, IDataLists dataLists)   {

        logger.debug("Creating DataFileLoader bean to load file:" + DATA_FILE_PATH);

        return new DataFileLoader(DATA_FILE_PATH, personDAO, medicalRecordDAO, firestationDAO, dataLists);
    }
}
