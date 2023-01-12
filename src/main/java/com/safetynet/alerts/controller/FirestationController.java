package com.safetynet.alerts.controller;

import com.safetynet.alerts.dao.IFirestationDAO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    private static final Logger logger = LogManager.getLogger(FirestationController.class);

    @Autowired
    IFirestationDAO firestationDAO;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createFirestation(@RequestBody Firestation newFirestation, UriComponentsBuilder uriComponentsBuilder) {

        if (isEmpty(newFirestation.getAddress()) || isEmpty(newFirestation.getStationNumber())) {
            logger.error("Invalid post request: empty fields: address or number");

            return ResponseEntity.badRequest()
                    .body("400 Bad request: address or station number should not be empty");

        } else {
            logger.info("Successful post request: saving new firestation in repository: address: {}, firestation {}", newFirestation.getAddress(), newFirestation.getStationNumber());
            logger.debug(newFirestation.toString());
            firestationDAO.save(newFirestation);

            return ResponseEntity
                    .created(uriComponentsBuilder.build(newFirestation))
                    .body("201 Successful post request");
        }

    }

    @PutMapping(path = "{address}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Firestation updateFirestation(@PathVariable("address") final String address, @RequestBody String station) {
        Optional<String> e = Optional.ofNullable(firestationDAO.get(address));

        if (e.isPresent() && address != null) {
            String currentAddress = e.get();

            Firestation currentFirestation = new Firestation(currentAddress, station);

            logger.info("Successful put request: saving new firestation in repository with address: {}", currentAddress);
            logger.debug(currentFirestation.toString());

            firestationDAO.save(currentFirestation);

            return currentFirestation;

        } else {

            return null;
        }
    }

    @DeleteMapping("/{address}")
    public String deleteFirestationAtAddress(@PathVariable("address") final String address) {
        String station = firestationDAO.delete(address);

        if (station != null) {
            logger.info("Successful delete request: deleted firestation of address: {}", address);
        }
        else {
            logger.info("Delete request ineffective: no station in repository of address: {}", address);
        }

        return station;
    }

}
