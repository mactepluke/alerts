package com.safetynet.alerts.controller;

import com.safetynet.alerts.dao.IFirestationDAO;
import com.safetynet.alerts.model.Firestation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    private static final Logger logger = LogManager.getLogger(FirestationController.class);

    @Autowired
    IFirestationDAO firestationDAO;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPerson(@RequestBody Firestation newFirestation, UriComponentsBuilder uriComponentsBuilder) {

        if (isEmpty(newFirestation.getAddress()) || isEmpty(newFirestation.getStationNumber())) {
            logger.error("Invalid post request: empty fields: address or number");

            return ResponseEntity.badRequest()
                    .body("400 Bad request: address or station number should not be empty");

        } else {
            logger.info("Successful post request: saving new firestation in repository: address: {}, firestation {}", newFirestation.getAddress(), newFirestation.getStationNumber());
            firestationDAO.saveFirestation(newFirestation);

            return ResponseEntity
                    .created(uriComponentsBuilder.build(newFirestation))
                    .body("201 Successful post request");
        }

    }

}
