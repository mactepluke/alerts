package com.safetynet.alerts.unit;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class PersonEndpointsTest {

    private static final Logger logger = LogManager.getLogger(PersonEndpointsTest.class);

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IPersonService personService;
    private Person testPerson;

    @BeforeAll
    void setUp() {
        logger.info("*** STARTING PERSON ENDPOINTS TESTS ***");
        testPerson = new Person("John", "Doe");
    }

    @AfterAll
    void tearDown() {
        logger.info("*** PERSON ENDPOINTS TESTS FINISHED ***");
    }

    // Tests for endpoint: http://localhost:8080/person
    @Test
    @DisplayName("Add a person")
    void AddPerson() throws Exception {

        when(personService.create(any(Person.class))).thenReturn(testPerson);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":" + "\"" + testPerson.getFirstName() + "\"" + ",\"lastName\":" + "\"" + testPerson.getLastName() + "\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("Update a person")
    void UpdatePerson() throws Exception {

        when(personService.update(anyString(), any(Person.class))).thenReturn(testPerson);

        mockMvc.perform(put("/person/{id}", testPerson.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":" + "\"" + testPerson.getFirstName() + "\"" + ",\"lastName\":" + "\"" + testPerson.getLastName() + "\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete a person")
    void DeletePerson() throws Exception {

        when(personService.delete(anyString())).thenReturn(testPerson);

        mockMvc.perform(delete("/person/{id}", testPerson.getId()))
                .andExpect(status().isOk());
    }

}