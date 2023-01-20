package com.safetynet.alerts.service;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static com.safetynet.alerts.configuration.DataConfig.getApplicationProperty;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdvancedRequestServiceTest {

    private static final Logger logger = LogManager.getLogger(AdvancedRequestServiceTest.class);

    @Autowired
    private IDataFileLoader dataFileLoader;
    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private AdvancedRequestService ars;


    @BeforeAll
    void setUp() {

        logger.info("*** SETTING UP SERVICE PACKAGE INTEGRATION TESTS ***");

        String dataFilePath = getApplicationProperty("testdatafilepath");

        logger.debug("Loading data file: {}", dataFilePath);

        DataRepository testRepository;
        testRepository = dataFileLoader.loadDataFile(dataFilePath);
        dataRepository.copyRepository(testRepository);
    }


    @AfterAll
    void showLogs() {

        logger.info("*** SERVICE PACKAGE INTEGRATION TESTS FINISHED ***");
        logger.debug("Displaying DataRepository content:");
        logger.debug(dataRepository);
    }


    @Test
    @DisplayName("Fetch Persons By Firestation")
    void fetchPersonsByFirestation() {

        PersonsByFirestation personsByFirestation = ars.fetchPersonsByFirestation("80");

        assertEquals(3, personsByFirestation.getCoveredPersonsList().size());
        assertEquals(2, personsByFirestation.getAdults());
        assertEquals(1, personsByFirestation.getChild());
    }

    @Test
    @DisplayName("Fetch Child From Address")
    void fetchChildFromAddress() {

        ChildFromAddress childFromAddress = ars.fetchChildFromAddress("John's address");

        assertEquals(1, childFromAddress.getChild().size());
        assertEquals(2, childFromAddress.getOthers().size());
    }

    @Test
    @DisplayName("Fetch Persons Phone By Firestation")
    void fetchPersonsPhoneByFirestation() {

        PersonsPhoneByFirestation personsPhoneByFirestation = ars.fetchPersonsPhoneByFirestation("3");

        assertEquals(2, personsPhoneByFirestation.getPhones().size());
        assertTrue(personsPhoneByFirestation.getPhones().contains("841-874-6513"));
    }

    @Test
    @DisplayName("Fetch Persons And Firestation From Address")
    void fetchPersonsAndFirestationFromAddress() {

        PersonsAndFirestationFromAddress personsAndFirestationFromAddress = ars.fetchPersonsAndFirestationFromAddress("1509 Culver St");

        assertEquals("3", personsAndFirestationFromAddress.getStation());
        assertEquals(2, personsAndFirestationFromAddress.getPersonsLivingAtAddress().size());
    }

    @Test
    @DisplayName("Fetch Persons By Firestation Flood")
    void fetchPersonsByFirestationFlood() {

        PersonsByFirestationFlood personsByFirestationFlood = ars.fetchPersonsByFirestationFlood(List.of("2","3","80","666"));

        assertEquals(2, personsByFirestationFlood.getStations().size());
        assertEquals(3, personsByFirestationFlood.getStations().get("80").get("John's address").size());
    }

    @Test
    @DisplayName("Fetch Person Info")
    void fetchPersonInfo() {

        PersonInfo personInfo = ars.fetchPersonInfo("John", "Lennon");

        assertEquals(3, personInfo.getPersonInfo().size());
    }

    @Test
    @DisplayName("Fetch Persons Email By City")
    void fetchPersonsEmailByCity() {

        PersonsEmailByCity personsEmailByCity = ars.fetchPersonsEmailByCity("Culver");

        assertTrue(personsEmailByCity.getEmails().contains("jaboyd@email.com"));
    }
}
