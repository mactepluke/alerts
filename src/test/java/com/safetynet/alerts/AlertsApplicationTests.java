package com.safetynet.alerts;

import com.safetynet.alerts.dao.*;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.service.DataFileLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
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

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DataFileLoader dataFileLoader;
    @Autowired
    private IPersonDAO personDAO;
    @Autowired
    private IMedicalRecordDAO medicalRecordDAO;
    @Autowired
    private IFirestationDAO firestationDAO;
    @Autowired
    private DataRepository dataRepository;


    @BeforeEach
    void clearData() {
        dataRepository.resetTables();
    }

    @Test
    @DisplayName("Context loads")
    void contextLoads() {

    }

    @Test
    @DisplayName("Loads a test file to repository and requests the correct data")
    void LoadDataFileTest() {

        logger.debug("Loading data file:" + DATA_TEST_FILE_PATH);
        dataFileLoader.loadDataFile(DATA_TEST_FILE_PATH);
        logger.debug(dataRepository.toString());

        assertEquals("00100", personDAO.get("JerryTest").getZip());
        assertEquals("red pill:1kg", medicalRecordDAO.get("JerryTest").getMedications().get(1));
        assertEquals("10", firestationDAO.get("666 Dangerous Neighborhood"));
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
        personDAO.save(testPerson);

        mockMvc.perform(MockMvcRequestBuilders.put("/person/{id}", testPerson.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Big Apple Street\", \"city\":\"NYC\", \"zip\":\"95230\", \"phone\":\"555-177-845\", \"email\":\"test@gmail.com\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Big Apple Street")));
    }

    @Test
    @DisplayName("Deletes a person via endpoint")
    void DeletePersonTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/person/{id}", "TestPerson"))
                .andExpect(status().isOk());

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
    void UpdateMedicalRepositoryDataTest() throws Exception {

        MedicalRecord testMedicalRecord = new MedicalRecord("testFirstName", "testLastName");
        medicalRecordDAO.save(testMedicalRecord);

        mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord/{id}", testMedicalRecord.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"birthdate\":\"11/08/1980\", \"allergies\":[\"dust\", \"caviar\"]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("caviar")));
    }

    @Test
    @DisplayName("Deletes a medical record via endpoint")
    void DeleteMedicalRecordTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord/{id}", "TestMedicalRecord"))
                .andExpect(status().isOk());

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
    void UpdateFirestationNumberTest() throws Exception {

        Firestation testFirestation = new Firestation("Maple St", "1");
        firestationDAO.save(testFirestation);

        mockMvc.perform(MockMvcRequestBuilders.put("/firestation/{address}", testFirestation.getAddress())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Maple St\", \"station\":\"2\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("2")));
    }

    @Test
    @DisplayName("Deletes a firestation and address via endpoint")
    void DeleteFirestationAndAddressByAddressTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/firestation/{address}", "Test St"))
                .andExpect(status().isOk());

    }

    @Test
    @Disabled("Not implemented")
    @DisplayName("Deletes all firestation/address maps by firestation number via endpoint")
    void DeleteAllFirestationAndAddressMapByNumberTest() {
        fail("Test not yet implemented");
    }

}
