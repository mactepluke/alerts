package com.safetynet.alerts.integration;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.service.*;
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
class ServiceITests {

    private static final Logger logger = LogManager.getLogger(ServiceITests.class);

    @Autowired
    private IDataFileLoader dataFileLoader;
    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private AdvancedRequestService ars;
    @Autowired
    private PersonService personService;
    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired
    private FirestationService firestationService;

    private Person testPerson, newPerson;
    private MedicalRecord testMedicalRecord, newMedicalRecord;
    private Firestation testFirestation;
    private final static String TEST_FIRST_NAME = "Johnny";
    private final static String TEST_LAST_NAME = "Walker";
    private final static String TEST_ADDRESS = "1820 Scotland St";
    private final static String TEST_BIRTHDATE = "11/08/1980";
    private final static String TEST_STATION = "12";
    private final static String TEST_NEW_STATION = "999";


    @BeforeAll
    void setUp() {

        logger.info("*** STARTING ADVANCED SERVICE INTEGRATION TESTS ***");

        String dataFilePath = getApplicationProperty("testdatafilepath");
        logger.debug("Loading data file: {}", dataFilePath);

        DataRepository testRepository;
        testRepository = dataFileLoader.loadDataFile(dataFilePath);
        dataRepository.copyRepository(testRepository);

        testPerson = new Person(TEST_FIRST_NAME, TEST_LAST_NAME);
        newPerson = new Person(TEST_FIRST_NAME, TEST_LAST_NAME);
        newPerson.setAddress(TEST_ADDRESS);

        testMedicalRecord = new MedicalRecord(TEST_FIRST_NAME, TEST_LAST_NAME);
        newMedicalRecord = new MedicalRecord(TEST_FIRST_NAME, TEST_LAST_NAME);
        newMedicalRecord.setBirthdate(TEST_BIRTHDATE);

        testFirestation = new Firestation(TEST_ADDRESS, TEST_STATION);

    }


    @AfterAll
    void tearDown() {
        logger.info("*** ADVANCED SERVICE INTEGRATION TESTS FINISHED ***");
        logger.debug("Displaying mock DataRepository content:");
        logger.debug(dataRepository);
    }

    @Nested
    class AdvancedRequestsServiceTests {


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

            ChildrenFromAddress childrenFromAddress = ars.fetchChildrenFromAddress("John's address");

            assertEquals(1, childrenFromAddress.getChild().size());
            assertEquals(2, childrenFromAddress.getOthers().size());
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

            PersonsByFirestationFlood personsByFirestationFlood = ars.fetchPersonsByFirestationFlood(List.of("2", "3", "80", "666"));

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

    @Nested
    class MiscServiceTests {

        @Test
        @DisplayName("Adds, updates and deletes a person")
        void personServiceTest() {

            assertNull(personService.create(testPerson));

            assertEquals(TEST_ADDRESS, personService
                    .delete(personService
                            .update(testPerson
                                    .getId(), newPerson)
                            .getId())
                    .getAddress());
        }

        @Test
        @DisplayName("Adds, updates and deletes a medical record")
        void medicalRecordServiceTest() {

            assertNull(medicalRecordService.create(testMedicalRecord));

            assertEquals(TEST_BIRTHDATE, medicalRecordService
                    .delete(medicalRecordService
                            .update(testMedicalRecord
                                    .getId(), newMedicalRecord)
                            .getId())
                    .getBirthdate());
        }

        @Test
        @DisplayName("Adds, updates and deletes a firestation")
        void firestationServiceTest() {

            assertNull(firestationService.create(testFirestation));

            assertEquals(TEST_NEW_STATION, firestationService
                    .delete(firestationService
                    .update(testFirestation
                            .getAddress(), TEST_NEW_STATION)
                    .getAddress())
                    .getStationNumber());
        }

    }
}
