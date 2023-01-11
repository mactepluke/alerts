package com.safetynet.alerts.controller;

import com.safetynet.alerts.dao.IMedicalRecordDAO;
import com.safetynet.alerts.model.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private static final Logger logger = LogManager.getLogger(MedicalRecordController.class);

    @Autowired
    IMedicalRecordDAO medicalRecordDAO;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPerson(@RequestBody MedicalRecord newMedicalRecord, UriComponentsBuilder uriComponentsBuilder) {

        if (isEmpty(newMedicalRecord.getFirstName()) || isEmpty(newMedicalRecord.getLastName())) {
            logger.error("Invalid post request: empty fields: first name or last name");

            return ResponseEntity.badRequest()
                    .body("400 Bad request: firstname or lastname should not be empty");
        } else {
            logger.info("Successful post request: saving new medical record in repository with id: {}", newMedicalRecord.getId());
            medicalRecordDAO.saveMedicalRecord(newMedicalRecord);

            return ResponseEntity
                    .created(uriComponentsBuilder.build(newMedicalRecord))
                    .body("201 Successful post request");
        }

    }

}
