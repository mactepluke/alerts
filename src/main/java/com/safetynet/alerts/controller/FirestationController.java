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

import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    private static final Logger logger = LogManager.getLogger(FirestationController.class);

    @Autowired
    private IFirestationDAO firestationDAO;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createFirestation(@RequestBody Firestation newFirestation, UriComponentsBuilder uriComponentsBuilder) {

        if (isEmpty(newFirestation.getAddress()) || isEmpty(newFirestation.getStationNumber())) {
            logger.error("Invalid post request: empty fields: address or number");

            return ResponseEntity.badRequest()
                    .body("Bad request: address or station number should not be empty");

        } else {
            logger.info("Successful post request: saving new firestation in repository: address: {}, firestation {}", newFirestation.getAddress(), newFirestation.getStationNumber());
            firestationDAO.save(newFirestation);

            return ResponseEntity
                    .created(uriComponentsBuilder.build(newFirestation))
                    .body("Successful post request");
        }

    }

    @PutMapping(path = "{address}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Firestation updateFirestation(@PathVariable("address") final String address, @RequestBody String station) {
        Optional<String> e = Optional.ofNullable(firestationDAO.get(address));

        if (e.isPresent() && address != null) {

            Firestation currentFirestation = new Firestation(address, station);

            logger.info("Successful put request: saving new firestation in repository with address: {}", address);
            logger.debug(currentFirestation);

            firestationDAO.save(currentFirestation);

            return currentFirestation;

        } else {

            logger.error("Cannot update firestation: no firestation in repository with address: {}", address);
            return null;
        }
    }

    @DeleteMapping("/{value}")
    public String deleteFirestationAtAddress(@PathVariable("value") final String value) {
        String station = firestationDAO.delete(value);

        if (station != null) {
            logger.info("Successful delete request: deleted firestation of address: {}", value);
        }
        else {
            if (firestationDAO.deleteAllFirestationsOfNumber(value))    {
                logger.info("Successful delete request: deleted all firestations of number: {}", value);
            }   else {
                logger.error("Cannot delete firestation: no station in repository of address or number: {}", value);
            }
        }

        return station;
    }

}
