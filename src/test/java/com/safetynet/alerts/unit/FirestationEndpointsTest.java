package com.safetynet.alerts.unit;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.IFirestationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class FirestationEndpointsTest {

    private static final Logger logger = LogManager.getLogger(FirestationEndpointsTest.class);

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IFirestationService firestationService;
    private Firestation testFirestation;


    @BeforeAll
    void setUp() {
        logger.info("*** STARTING FIRESTATION ENDPOINTS TESTS ***");
        testFirestation = new Firestation("Test Rd", "66");
    }

    @AfterAll
    void tearDown() {
        logger.info("*** FIRESTATION ENDPOINTS TESTS FINISHED ***");
    }

        // Tests for endpoint: http://localhost:8080/firestation
    @Test
    @DisplayName("Add a firestation and address")
    void AddFirestationAndAddress() throws Exception {

        when(firestationService.create(any(Firestation.class))).thenReturn(testFirestation.getStationNumber());

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":" + "\"" + testFirestation.getAddress() + "\"" + ",\"station\":" + "\"" + testFirestation.getStationNumber() + "\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Update a firestation's number")
    void UpdateFirestationNumber() throws Exception {

        when(firestationService.update(anyString(), anyString())).thenReturn(testFirestation);

        mockMvc.perform(put("/firestation/{address}", testFirestation.getAddress())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testFirestation.getStationNumber())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete a firestation/address")
    void DeleteFirestation() throws Exception {

        when(firestationService.delete(anyString())).thenReturn(testFirestation);

        mockMvc.perform(delete("/firestation/{value}", testFirestation.getAddress()))
                .andExpect(status().isOk());
    }


}
