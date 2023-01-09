package com.safetynet.alerts;

import com.safetynet.alerts.dao.*;
import com.safetynet.alerts.service.IDataFileLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class AlertsApplicationTests {

    private static final Logger logger = LogManager.getLogger(AlertsApplicationTests.class);

    @Autowired
    IDataFileLoader dataFileLoader;

    @Autowired
    IPersonDAO personDAO;
    @Autowired
    IMedicalRecordDAO medicalRecordDAO;
    @Autowired
    IFirestationDAO firestationDAO;

    private static final String DATA_TEST_FILE_PATH = "src/main/resources/dataTest.json";

    @Test
    @DisplayName("Context loads")
    void contextLoads() {

    }

    @Test
    @DisplayName("Loads a mock file and tests requesting data")
    void DataFileLoaderTest() {
        dataFileLoader.loadDataFile(DATA_TEST_FILE_PATH);

        assertEquals("97451", personDAO.getPerson("JohnBoyd").getZip());
        assertEquals("hydrapermazol:100mg", medicalRecordDAO.getMedicalRecord("JohnBoyd").getMedications().get(1));
        assertEquals("3", firestationDAO.getFirestationNumber("1509 Culver St"));
    }

    // Tests for endpoint: http://localhost:8080/person
    @Test
    @Disabled
    @DisplayName("Adds a person to the repository via endpoint")
    void addPersonToRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled
    @DisplayName("Updates a person's data from the repository via endpoint")
    void updatePersonDataFromRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled
    @DisplayName("Deletes a person from the repository via endpoint")
    void deletePersonFromRepository() {
        fail("Test not yet implemented");
    }

    // Tests for endpoint: http://localhost:8080/firestation
    @Test
    @Disabled
    @DisplayName("Adds a firestation and address map to the repository via endpoint")
    void addFirestationAddressMapToRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled
    @DisplayName("Updates a firestation's number from the repository via endpoint")
    void updateFirestationNumberFromRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled
    @DisplayName("Deletes a firestation and address map by address from the repository via endpoint")
    void deleteFirestationAddressMapByAddressFromRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled
    @DisplayName("Deletes all firestation and address maps by firestation number from the repository via endpoint")
    void deleteAllFirestationAddressMapByNumberFromRepository() {
        fail("Test not yet implemented");
    }

    // Tests for endpoint: http://localhost:8080/medicalRecord
    @Test
    @Disabled
    @DisplayName("Adds a medical record to the repository via endpoint")
    void addMedicalRecordToRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled
    @DisplayName("Updates a medical record's data from the repository via endpoint")
    void updateMedicalRepositoryDataFromRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled
    @DisplayName("Deletes a medical record from the repository via endpoint")
    void deleteMedicalRecordFromRepository() {
        fail("Test not yet implemented");
    }

}
