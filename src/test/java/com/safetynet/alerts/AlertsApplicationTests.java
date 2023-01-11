package com.safetynet.alerts;

import com.safetynet.alerts.dao.*;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.DataFileLoader;
import com.safetynet.alerts.service.DataLists;
import com.safetynet.alerts.service.IDataLists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @Autowired
    private IPersonDAO personDAO;
    /*@MockBean
    private IPersonDAO personDAOTest;
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
    @DisplayName("Adds a person via endpoint")
    void AddPersonTest() throws Exception {

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"lastName\":\"Doe\", \"address\":\"Big Kahuna Burger road\", \"city\":\"L.A.\", \"zip\":\"90023\", \"phone\":\"555-177-845\", \"email\":\"john.doe@aol.com\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }


    @Test
    @DisplayName("Updates a person's data via endpoint")
    void UpdatePersonDataTest() throws Exception {

        Person testPerson = new Person("testFirstName", "testLastName");
        personDAO.savePerson(testPerson);

        /*MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/person/" + testPerson.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Big Apple Street\", \"city\":\"NYC\", \"zip\":\"95230\", \"phone\":\"555-177-845\", \"email\":\"testFirstName.testLastName@gmail.com\"}");
        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Person updated."))
                .andDo(MockMvcResultHandlers.print());*/


        mockMvc.perform(MockMvcRequestBuilders.put("/person/" + testPerson.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Big Apple Street\", \"city\":\"NYC\", \"zip\":\"95230\", \"phone\":\"555-177-845\", \"email\":\"test@gmail.com\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Big Apple Street")));

    }

    @Test
    @DisplayName("Deletes a person via endpoint")
    void DeletePersonTest() {
        fail("Test not yet implemented");
    }

    // Tests for endpoint: http://localhost:8080/firestation
    @Test
    @DisplayName("Adds a firestation and address via endpoint")
    void AddFirestationAndAddressTest() throws Exception {

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Big Kahuna Burger road\", \"station\":\"8\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Updates a firestation's number via endpoint")
    void UpdateFirestationNumberTest() {
        fail("Test not yet implemented");
    }

    @Test
    @DisplayName("Deletes a firestation and address via endpoint")
    void DeleteFirestationAndAddressByAddressTest() {
        fail("Test not yet implemented");
    }

    @Test
    @DisplayName("Deletes all firestation and address maps by firestation number via endpoint")
    void DeleteAllFirestationAndAddressMapByNumberTest() {
        fail("Test not yet implemented");
    }

    // Tests for endpoint: http://localhost:8080/medicalRecord
    @Test
    @DisplayName("Adds a medical record via endpoint")
    void AddMedicalRecordTest() throws Exception {

        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"lastName\":\"Doe\", \"birthdate\":\"Big Kahuna Burger road\", \"medications\":[\"modafinil\"], \"allergies\":[\"dust\", \"caviar\"]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("Updates a medical record's data via endpoint")
    void UpdateMedicalRepositoryDataTest() {
        fail("Test not yet implemented");
    }

    @Test
    @DisplayName("Deletes a medical record via endpoint")
    void DeleteMedicalRecordTest() {
        fail("Test not yet implemented");
    }

}
