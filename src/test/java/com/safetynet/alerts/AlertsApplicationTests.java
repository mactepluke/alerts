package com.safetynet.alerts;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.model.BlankPerson;
import com.safetynet.alerts.model.IPerson;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class AlertsApplicationTests {

    private static final Logger logger = LogManager.getLogger(AlertsApplicationTests.class);

    @Test
    @DisplayName("Context loads")
    void contextLoads()   {

    }

    @Test
    void JSoniterTest()    {
        IPerson person = JsonIterator.deserialize("{\"firstName\": \"John\", \"lastName\": \"Doe\", \"address\": \"HK\"}", BlankPerson.class);
        logger.debug(person.getFirstName());
        logger.debug(person.getLastName());
        logger.debug(person.getAddress());
        //person.attachment = Any.wrapArray(new int[]{1, 2, 3});
        logger.debug(JsonStream.serialize(person));
    }

    // Tests for endpoint: http://localhost:8080/person
    @Test
    @DisplayName("Adds a person to the repository via endpoint")
    void addPersonToRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @DisplayName("Updates a person's data from the repository via endpoint")
    void updatePersonDataFromRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @DisplayName("Deletes a person from the repository via endpoint")
    void deletePersonFromRepository() {
        fail("Test not yet implemented");
    }

    // Tests for endpoint: http://localhost:8080/firestation
    @Test
    @DisplayName("Adds a firestation and address map to the repository via endpoint")
    void addFirestationAddressMapToRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @DisplayName("Updates a firestation's number from the repository via endpoint")
    void updateFirestationNumberFromRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @DisplayName("Deletes a firestation and address map by address from the repository via endpoint")
    void deleteFirestationAddressMapByAddressFromRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @DisplayName("Deletes all firestation and address maps by firestation number from the repository via endpoint")
    void deleteAllFirestationAddressMapByNumberFromRepository() {
        fail("Test not yet implemented");
    }

    // Tests for endpoint: http://localhost:8080/medicalRecord
    @Test
    @DisplayName("Adds a medical record to the repository via endpoint")
    void addMedicalRecordToRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @DisplayName("Updates a medical record's data from the repository via endpoint")
    void updateMedicalRepositoryDataFromRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @DisplayName("Deletes a medical record from the repository via endpoint")
    void deleteMedicalRecordFromRepository() {
        fail("Test not yet implemented");
    }

}
