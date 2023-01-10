package com.safetynet.alerts;

import com.safetynet.alerts.dao.*;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.DataFileLoader;
import com.safetynet.alerts.service.DataLists;
import com.safetynet.alerts.service.IDataLists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@SpringBootTest
@AutoConfigureMockMvc
class AlertsApplicationTests {

    private static final Logger logger = LogManager.getLogger(AlertsApplicationTests.class);

    private static final String DATA_TEST_FILE_PATH = "src/test/resourcesTest/dataTest.json";

    /*@TestConfiguration
    static class TestConfig {

        @Bean
        public DataFileLoader createDataFileLoader(IPersonDAO personDAO, IMedicalRecordDAO medicalRecordDAO, IFirestationDAO firestationDAO, IDataLists dataLists) {

            logger.debug("Created test DataFileLoader object to load file:" + DATA_TEST_FILE_PATH);

            return new DataFileLoader(DATA_TEST_FILE_PATH, personDAO, medicalRecordDAO, firestationDAO, dataLists);
        }
    }*/


    @Autowired
    private MockMvc mockMvc;
    /*@MockBean
    private IPersonDAO personDAO;
    @MockBean
    private IMedicalRecordDAO medicalRecordDAO;
    @MockBean
    private IFirestationDAO firestationDAO;
    @MockBean
    private IDataLists dataListsTest;*/

    private final IPersonDAO personDAOTest = new PersonDAO();
    private final IMedicalRecordDAO medicalRecordDAOTest = new MedicalRecordDAO();
    private final IFirestationDAO firestationDAOTest = new FirestationDAO();

    @Test
    @DisplayName("Context loads")
    void contextLoads() {

    }

    @Test
    @DisplayName("Loads a test file to repository and requests the correct data")
    void DataFileLoaderTest() {

        IDataLists dataListsTest = new DataLists();
        new DataFileLoader(DATA_TEST_FILE_PATH, personDAOTest, medicalRecordDAOTest, firestationDAOTest, dataListsTest);

        logger.debug("Created test DataFileLoader object to load file:" + DATA_TEST_FILE_PATH);

        assertEquals("00100", personDAOTest.getPerson("JerryTest").getZip());
        assertEquals("red pill:1kg", medicalRecordDAOTest.getMedicalRecord("JerryTest").getMedications().get(1));
        assertEquals("10", firestationDAOTest.getFirestationNumber("666 Dangerous Neighborhood"));

    }

    // Tests for endpoint: http://localhost:8080/person
    @Test
    @Disabled("To be implemented")
    @DisplayName("Adds a person to the repository via endpoint")
    void addPersonToRepository() throws Exception {

        /*mockMvc.perform(post("/person"))
                .content(asJsonString(new EmployeeVO(null, "firstName4", "lastName4", "email4@mail.com")))
                //.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Laurent")));*/
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/person")
                        .content("{\"firstName\":\"tao\", \"lastName\":\"wen\", \"address\":\"test road\", \"city\":\"NYC\", \"zip\":\"50585\", \"phone\":\"555-001-698\", \"email\":\"john.doe@aol.com\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                /*.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").exists())*/;

    }


    @Test
    @Disabled("To be implemented")
    @DisplayName("Updates a person's data from the repository via endpoint")
    void updatePersonDataFromRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled("To be implemented")
    @DisplayName("Deletes a person from the repository via endpoint")
    void deletePersonFromRepository() {
        fail("Test not yet implemented");
    }

    // Tests for endpoint: http://localhost:8080/firestation
    @Test
    @Disabled("To be implemented")
    @DisplayName("Adds a firestation and address map to the repository via endpoint")
    void addFirestationAddressMapToRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled("To be implemented")
    @DisplayName("Updates a firestation's number from the repository via endpoint")
    void updateFirestationNumberFromRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled("To be implemented")
    @DisplayName("Deletes a firestation and address map by address from the repository via endpoint")
    void deleteFirestationAddressMapByAddressFromRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled("To be implemented")
    @DisplayName("Deletes all firestation and address maps by firestation number from the repository via endpoint")
    void deleteAllFirestationAddressMapByNumberFromRepository() {
        fail("Test not yet implemented");
    }

    // Tests for endpoint: http://localhost:8080/medicalRecord
    @Test
    @Disabled("To be implemented")
    @DisplayName("Adds a medical record to the repository via endpoint")
    void addMedicalRecordToRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled("To be implemented")
    @DisplayName("Updates a medical record's data from the repository via endpoint")
    void updateMedicalRepositoryDataFromRepository() {
        fail("Test not yet implemented");
    }

    @Test
    @Disabled("To be implemented")
    @DisplayName("Deletes a medical record from the repository via endpoint")
    void deleteMedicalRecordFromRepository() {
        fail("Test not yet implemented");
    }

}
