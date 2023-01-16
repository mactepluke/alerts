package com.safetynet.alerts;

import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.service.IDataFileLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static com.safetynet.alerts.configuration.DataConfig.getApplicationProperty;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class AlertsApplicationTests {

    private static final Logger logger = LogManager.getLogger(AlertsApplicationTests.class);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    IDataFileLoader dataFileLoader;
    @Autowired
    private DataRepository dataRepository;


    @BeforeAll
    void setUp() {

        logger.info("*** SETTING UP JUNIT TESTS ***");

        String dataFilePath = getApplicationProperty("testdatafilepath");

        logger.debug("Loading data file: {}", dataFilePath);

        DataRepository testRepository;
        testRepository = dataFileLoader.loadDataFile(dataFilePath);
        dataRepository.copyRepository(testRepository);
    }


    @AfterAll
    void showLogs() {

        logger.info("*** JUNIT TESTS FINISHED ***");
        logger.debug("Displaying DataRepository content:");
        logger.debug(dataRepository);
    }

    @Test
    @DisplayName("Context loads")
    void contextLoads() {
    }


@Nested
@DisplayName("Basic Endpoints requests")
class BasicEndpointsRequests {

    // Tests for endpoint: http://localhost:8080/person
    @Test
    @DisplayName("Add a person")
    void AddPersonEndpointTest() throws Exception {

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"lastName\":\"Doe\", \"address\":\"Big Kahuna Burger road\", \"city\":\"L.A.\", \"zip\":\"90023\", \"phone\":\"555-177-845\", \"email\":\"john.doe@aol.com\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("Update a person's data")
    void UpdatePersonDataEndpointTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/person/{id}", "testFirstNametestLastName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Big Apple Street\", \"city\":\"NYC\", \"zip\":\"95230\", \"phone\":\"555-177-845\", \"email\":\"test@gmail.com\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete a person")
    void DeletePersonEndpointTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/person/{id}", "JerryTest"))
                .andExpect(status().isOk());
    }

    // Tests for endpoint: http://localhost:8080/medicalRecord
    @Test
    @DisplayName("Add a medical record")
    void AddMedicalRecordEndpointTest() throws Exception {

        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"lastName\":\"Doe\", \"birthdate\":\"Big Kahuna Burger road\", \"medications\":[\"modafinil\"], \"allergies\":[\"dust\", \"caviar\"]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Update a medical record's data")
    void UpdateMedicalRepositoryDataEndpointTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord/{id}", "testFirstNametestLastName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"birthdate\":\"11/08/1980\", \"allergies\":[\"dust\", \"caviar\"]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete a medical record")
    void DeleteMedicalRecordEndpointTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord/{id}", "TestMedicalRecord"))
                .andExpect(status().isOk());
    }

    // Tests for endpoint: http://localhost:8080/firestation
    @Test
    @DisplayName("Add a firestation and address")
    void AddFirestationAndAddressEndpointTest() throws Exception {

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Big Kahuna Burger road\", \"station\":\"8\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Update a firestation's number")
    void UpdateFirestationNumberEndpointTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/firestation/{address}", "Maple St")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete a firestation/address")
    void DeleteFirestationEndpointTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/firestation/{value}", "666 Dangerous Neighborhood"))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.delete("/firestation/{value}", "4"))
                .andExpect(status().isOk());
    }
}

    @Nested
    @DisplayName("Advanced Endpoints requests")
    class AdvancedEndpointsRequests {

        // http://localhost:8080/firestation?stationNumber=<station_number>
        @Test
        @DisplayName("Get the persons covered by a firestation")
        void getPersonsByFirestationEndpointTest() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get("/firestation?stationNumber=3"))
                    .andExpect(status().isOk());
        }

    }
}
