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

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private static final Logger logger = LogManager.getLogger(MedicalRecordController.class);

    @Autowired
    private IMedicalRecordDAO medicalRecordDAO;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createMedicalRecord(@RequestBody MedicalRecord newMedicalRecord, UriComponentsBuilder uriComponentsBuilder) {

        if (isEmpty(newMedicalRecord.getFirstName()) || isEmpty(newMedicalRecord.getLastName())) {
            logger.error("Invalid post request: empty fields: first name or last name");

            return ResponseEntity.badRequest()
                    .body("Bad request: firstname or lastname should not be empty");
        } else {
            logger.info("Successful post request: saving new medical record in repository with id: {}", newMedicalRecord.getId());
            logger.debug(newMedicalRecord);
            medicalRecordDAO.save(newMedicalRecord);

            return ResponseEntity
                    .created(uriComponentsBuilder.build(newMedicalRecord))
                    .body("Successful post request");
        }

    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MedicalRecord updateMedicalRecord(@PathVariable("id") final String id, @RequestBody MedicalRecord medicalRecord) {
        Optional<MedicalRecord> e = Optional.ofNullable(medicalRecordDAO.get(id));

        if (e.isPresent()) {

            MedicalRecord currentMedicalRecord = e.get();

            String birthdate = medicalRecord.getBirthdate();
            if (birthdate != null) {
                currentMedicalRecord.setBirthdate(birthdate);
            }

            List<String> medications = medicalRecord.getMedications();
            if (medications != null) {

                for (String medication : medications) {
                    currentMedicalRecord.addMedication(medication);
                }
            }

            List<String> allergies = medicalRecord.getAllergies();
            if (allergies != null) {

                for (String allergy : allergies) {
                    currentMedicalRecord.addAllergy(allergy);
                }
            }


            logger.info("Successful put request: saving new medical record in repository with id: {}", id);
            logger.debug(currentMedicalRecord);

            medicalRecordDAO.save(currentMedicalRecord);

            return currentMedicalRecord;

        } else {
            logger.error("Cannot update medical record: no medical record in repository with id: {}", id);
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public MedicalRecord deleteMedicalRecord(@PathVariable("id") final String id) {
        MedicalRecord medicalRecord = medicalRecordDAO.delete(id);

        if (medicalRecord != null) {
            logger.info("Successful delete request: deleted medical record with id: {}", id);
        }
        else {
            logger.error("Cannot delete medical record: no medical record in repository with id: {}", id);
        }

        return medicalRecord;

    }

}
