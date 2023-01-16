package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;
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
    private IMedicalRecordService medicalRecordService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody MedicalRecord newMedicalRecord, UriComponentsBuilder uriComponentsBuilder) {

        logger.info("Post request received: create a new medical record in repository");

        if (isEmpty(newMedicalRecord.getFirstName()) || isEmpty(newMedicalRecord.getLastName())) {
            logger.error("Invalid post request: empty fields: first name or last name");

            return ResponseEntity.badRequest()
                    .body("Bad request: firstname or lastname should not be empty");
        } else {
            logger.info("Successful post request: saving new medical record in repository with id: {}", newMedicalRecord.getId());

            logger.debug(newMedicalRecord);
            medicalRecordService.create(newMedicalRecord);

            return ResponseEntity
                    .created(uriComponentsBuilder.build(newMedicalRecord))
                    .body("Successful post request");
        }

    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MedicalRecord update(@PathVariable("id") final String id, @RequestBody MedicalRecord medicalRecord) {

        logger.info("Put request received: update a medical record in repository");

        MedicalRecord newMedicalRecord = medicalRecordService.update(id, medicalRecord);

        if(newMedicalRecord != null) {
            logger.info("Successful put request: saving new medical record in repository with id: {}", id);
            logger.debug(newMedicalRecord);
        } else {
            logger.error("Cannot update medical record: no medical record in repository with id: {}", id);

        }
        return newMedicalRecord;
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MedicalRecord delete(@PathVariable("id") final String id) {

        logger.info("Delete request received: delete a medical record from repository");

        MedicalRecord medicalRecord = medicalRecordService.delete(id);

        if (medicalRecord != null) {
            logger.info("Successful delete request: deleted medical record with id: {}", id);
        }
        else {
            logger.error("Cannot delete medical record: no medical record in repository with id: {}", id);
        }

        return medicalRecord;

    }

}
