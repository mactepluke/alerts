package com.safetynet.alerts.controller;

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
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static com.safetynet.alerts.configuration.DataConfig.getApplicationProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class EndpointUnitTests {

    private static final Logger logger = LogManager.getLogger(EndpointUnitTests.class);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IDataFileLoader dataFileLoader;
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
    void AddPerson() throws Exception {

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"lastName\":\"Doe\", \"address\":\"Big Kahuna Burger road\", \"city\":\"L.A.\", \"zip\":\"90023\", \"phone\":\"555-177-845\", \"email\":\"john.doe@aol.com\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("Update a person's data")
    void UpdatePerson() throws Exception {

        mockMvc.perform(put("/person/{id}", "testFirstNametestLastName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Big Apple Street\", \"city\":\"NYC\", \"zip\":\"95230\", \"phone\":\"555-177-845\", \"email\":\"test@gmail.com\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete a person")
    void DeletePerson() throws Exception {

        mockMvc.perform(delete("/person/{id}", "JerryTest"))
                .andExpect(status().isOk());
    }

    // Tests for endpoint: http://localhost:8080/medicalRecord
    @Test
    @DisplayName("Add a medical record")
    void AddMedicalRecord() throws Exception {

        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"lastName\":\"Doe\", \"birthdate\":\"12/28/1960\", \"medications\":[\"modafinil\"], \"allergies\":[\"dust\", \"caviar\"]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Update a medical record's data")
    void UpdateMedicalRepositoryData() throws Exception {

        mockMvc.perform(put("/medicalRecord/{id}", "testFirstNametestLastName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"birthdate\":\"11/08/1980\", \"allergies\":[\"dust\", \"caviar\"]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete a medical record")
    void DeleteMedicalRecord() throws Exception {

        mockMvc.perform(delete("/medicalRecord/{id}", "TestMedicalRecord"))
                .andExpect(status().isOk());
    }

    // Tests for endpoint: http://localhost:8080/firestation
    @Test
    @DisplayName("Add a firestation and address")
    void AddFirestationAndAddress() throws Exception {

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Big Kahuna Burger road\", \"station\":\"8\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Update a firestation's number")
    void UpdateFirestationNumber() throws Exception {

        mockMvc.perform(put("/firestation/{address}", "Maple St")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete a firestation/address")
    void DeleteFirestation() throws Exception {

        mockMvc.perform(delete("/firestation/{value}", "666 Dangerous Neighborhood"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/firestation/{value}", "4"))
                .andExpect(status().isOk());
    }
}

    @Nested
    @DisplayName("Advanced Endpoints requests")
    class AdvancedEndpointsRequests {

        // http://localhost:8080/firestation?stationNumber=<station_number>
        @Test
        @DisplayName("Get the persons covered by a firestation")
        void getPersonsByFirestation() throws Exception {

            mockMvc.perform(get("/firestation?stationNumber=3"))
                    .andExpect(status().isOk());
        }

        //http://localhost:8080/childAlert?address=<address>
        @Test
        @DisplayName("Get the child living at an address")
        void getChildFromAddress() throws Exception {

            mockMvc.perform(get("/childAlert?address=Maple St"))
                    .andExpect(status().isOk());
        }

        //http://localhost:8080/phoneAlert?firestation=<firestation_number>
            @Test
            @DisplayName("Get the persons' phone who are covered by a firestation")
            void getPersonsPhoneByFirestation() throws Exception {

                mockMvc.perform(get("/phoneAlert?firestation=3"))
                        .andExpect(status().isOk());
            }

        //http://localhost:8080/fire?address=<address>
                @Test
                @DisplayName("Get the persons living at an address and its firestation")
                void getPersonsAndFirestationFromAddress() throws Exception {

                    mockMvc.perform(get("/fire?address=1509 Culver St"))
                            .andExpect(status().isOk());

                }
        //http://localhost:8080/flood/stations?stations=<a list of station_numbers>
                    @Test
                    @DisplayName("Get the persons by address who are covered by a list of firestations")
                    void getPersonsByFirestationFlood() throws Exception {

                        mockMvc.perform(get("/flood/stations?stations=1,2,3"))
                                .andExpect(status().isOk());
                    }
        //http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
                        @Test
                        @DisplayName("Get a persons' info and medical conditions by name")
                        void getPersonInfo() throws Exception {

                            mockMvc.perform(get("/personInfo?firstName=testFirstName&lastName=testLastName"))
                                    .andExpect(status().isOk());
                        }
        //http://localhost:8080/communityEmail?city=<city>
                            @Test
                            @DisplayName("Get emails of persons living in a city")
                            void getPersonsEmailByCity() throws Exception {

                                mockMvc.perform(get("/communityEmail?city=Culver"))
                                        .andExpect(status().isOk());

                            }
    }
}
