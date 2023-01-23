package com.safetynet.alerts.unit;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class MedicalRecordEndpointsTest {

    private static final Logger logger = LogManager.getLogger(MedicalRecordEndpointsTest.class);

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IMedicalRecordService medicalRecordService;
    private MedicalRecord testMedicalRecord;

    @BeforeAll
    void setUp() {
        logger.info("*** STARTING MEDICAL RECORD ENDPOINTS TESTS ***");
        testMedicalRecord = new MedicalRecord("John", "Doe");
    }

    @AfterAll
    void tearDown() {
        logger.info("*** MEDICAL RECORD ENDPOINTS TESTS FINISHED ***");
    }

    // Tests for endpoint: http://localhost:8080/medicalRecord
    @Test
    @DisplayName("Add a medical record")
    void AddMedicalRecord() throws Exception {

        when(medicalRecordService.create(any(MedicalRecord.class))).thenReturn(testMedicalRecord);

        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":" + "\"" + testMedicalRecord.getFirstName() + "\"" + ",\"lastName\":" + "\"" + testMedicalRecord.getLastName() + "\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Update a medical record")
    void UpdateMedicalRepositoryData() throws Exception {

        when(medicalRecordService.update(anyString(), any(MedicalRecord.class))).thenReturn(testMedicalRecord);

        mockMvc.perform(put("/medicalRecord/{id}", testMedicalRecord.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":" + "\"" + testMedicalRecord.getFirstName() + "\"" + ",\"lastName\":" + "\"" + testMedicalRecord.getLastName() + "\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete a medical record")
    void DeleteMedicalRecord() throws Exception {

        when(medicalRecordService.delete(anyString())).thenReturn(testMedicalRecord);

        mockMvc.perform(delete("/medicalRecord/{id}", testMedicalRecord.getId()))
                .andExpect(status().isOk());
    }

}
