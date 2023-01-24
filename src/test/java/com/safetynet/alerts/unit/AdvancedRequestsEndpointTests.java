package com.safetynet.alerts.unit;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.AdvancedRequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class AdvancedRequestsEndpointTests {

    private static final Logger logger = LogManager.getLogger(AdvancedRequestsEndpointTests.class);

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AdvancedRequestService ars;
    private Person testPerson;
    private Firestation testFirestation;
    private ArrayList<String> stationsList;
    private String city;


    @BeforeAll
    void setUp() {
        logger.info("*** STARTING ADVANCED ENDPOINTS TESTS ***");
        testPerson = new Person("John", "Doe");
        testFirestation = new Firestation("Test Rd", "66");
        stationsList = new ArrayList<>(List.of("2", "3", "4"));
        city = "NYC";
    }

    @AfterAll
    void tearDown() {
        logger.info("*** ADVANCED ENDPOINTS TESTS FINISHED ***");
    }


    // http://localhost:8080/firestation?stationNumber=<station_number>
    @Test
    @DisplayName("Get persons by firestation")
    void getPersonsByFirestation() throws Exception {

        when(ars.fetchPersonsByFirestation(anyString())).thenReturn(null);

        mockMvc.perform(get("/firestation?stationNumber=" + testFirestation.getStationNumber()))
                .andExpect(status().isOk());
    }

    //http://localhost:8080/childAlert?address=<address>
    @Test
    @DisplayName("Get child from address")
    void getChildFromAddress() throws Exception {

        when(ars.fetchChildrenFromAddress(anyString())).thenReturn(null);

        mockMvc.perform(get("/childAlert?address=" + testPerson.getAddress()))
                .andExpect(status().isOk());
    }

    //http://localhost:8080/phoneAlert?firestation=<firestation_number>
    @Test
    @DisplayName("Get persons' phone by firestation")
    void getPersonsPhoneByFirestation() throws Exception {

        when(ars.fetchPersonsPhoneByFirestation(anyString())).thenReturn(null);

        mockMvc.perform(get("/phoneAlert?firestation=" + testFirestation.getStationNumber()))
                .andExpect(status().isOk());
    }

    //http://localhost:8080/fire?address=<address>
    @Test
    @DisplayName("Get persons and firestation by address")
    void getPersonsAndFirestationFromAddress() throws Exception {

        when(ars.fetchPersonsAndFirestationFromAddress(anyString())).thenReturn(null);

        mockMvc.perform(get("/fire?address=" + testFirestation.getAddress()))
                .andExpect(status().isOk());
    }

    //http://localhost:8080/flood/stations?stations=<a list of station_numbers>
    @Test
    @DisplayName("Get persons by firestations list (flood)")
    void getPersonsByFirestationFlood() throws Exception {

        when(ars.fetchPersonsByFirestationFlood(Collections.singletonList(anyString()))).thenReturn(null);

        mockMvc.perform(get("/flood/stations?stations=" + String.join(",", stationsList)))
                .andExpect(status().isOk());
    }

    //http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
    @Test
    @DisplayName("Get person info")
    void getPersonInfo() throws Exception {

        when(ars.fetchPersonInfo(anyString(), anyString())).thenReturn(null);

        mockMvc.perform(get("/personInfo?firstName=" + testPerson.getFirstName() + "&lastName=" + testPerson.getLastName()))
                .andExpect(status().isOk());
    }

    //http://localhost:8080/communityEmail?city=<city>
    @Test
    @DisplayName("Get person email from city")
    void getPersonsEmailByCity() throws Exception {

        when(ars.fetchPersonsEmailByCity(anyString())).thenReturn(null);

        mockMvc.perform(get("/communityEmail?city=" + city))
                .andExpect(status().isOk());
    }
}
