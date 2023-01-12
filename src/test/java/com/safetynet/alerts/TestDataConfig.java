/*package com.safetynet.alerts;

import com.safetynet.alerts.dao.IFirestationDAO;
import com.safetynet.alerts.dao.IMedicalRecordDAO;
import com.safetynet.alerts.dao.IPersonDAO;
import com.safetynet.alerts.service.DataFileLoader;
import com.safetynet.alerts.service.IDataLists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestDataConfig {

    private static final Logger logger = LogManager.getLogger(TestDataConfig.class);
    private static final String DATA_FILE_PATH = "src/test/resources/dataTest.json";

    @Bean
    public DataFileLoader createDataFileLoader(IPersonDAO personDAO, IMedicalRecordDAO medicalRecordDAO, IFirestationDAO firestationDAO, IDataLists dataLists)   {

        logger.debug("Creating DataFileLoader bean to load file:" + DATA_FILE_PATH);

        return new DataFileLoader(DATA_FILE_PATH, personDAO, medicalRecordDAO, firestationDAO, dataLists);
    }
}*/
