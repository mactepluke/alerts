/*
package com.safetynet.alerts;

import com.safetynet.alerts.dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
@Import(TestDataConfig.class)
class AlertsApplicationTestsIT {

    private static final Logger logger = LogManager.getLogger(AlertsApplicationTestsIT.class);


    @Autowired
    private IPersonDAO personDAO;
    @Autowired
    private IMedicalRecordDAO medicalRecordDAO;
    @Autowired
    private IFirestationDAO firestationDAO;


    @Test
    @DisplayName("(IT) Loads a test file to repository and requests the correct data")
    void DataFileLoaderTestIT() {

        logger.debug("Created test DataFileLoader object to load test file");

        assertEquals("00100", personDAO.getPerson("JerryTest").getZip());
        assertEquals("red pill:1kg", medicalRecordDAO.getMedicalRecord("JerryTest").getMedications().get(1));
        assertEquals("10", firestationDAO.getFirestationNumber("666 Dangerous Neighborhood"));

    }
}

*/