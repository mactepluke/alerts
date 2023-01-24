package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.IFirestationService;
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
    private IFirestationService firestationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody Firestation newFirestation, UriComponentsBuilder uriComponentsBuilder) {

        logger.info("Post request received: create a new firestation in repository");
        String result;

        if (isEmpty(newFirestation.getAddress()) || isEmpty(newFirestation.getStationNumber())) {
            logger.error("Invalid post request: empty fields: address or number");

            return ResponseEntity.badRequest()
                    .body("Bad request: address or station number should not be empty");

        } else {

            result = firestationService.create(newFirestation);

            if (result != null) {
                logger.info("Unsuccessful post request: firestation {} already exists in repository at address: {}", result, newFirestation.getAddress());
                return ResponseEntity
                        .created(uriComponentsBuilder.build(newFirestation))
                        .body("Unsuccessful post request");
            }
            else {
                logger.info("Successful post request: saving new firestation in repository: address: {}, station: {}", newFirestation.getAddress(), newFirestation.getStationNumber());

                return ResponseEntity
                        .created(uriComponentsBuilder.build(newFirestation))
                        .body("Successful post request");
            }

        }
    }

    @PutMapping(path = "{address}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Firestation update(@PathVariable("address") final String address, @RequestBody String station) {

        logger.info("Put request received: update a firestation in repository");

        Firestation newFirestation = firestationService.update(address, station);

        if (newFirestation != null) {

            logger.info("Successful put request: updating firestation in repository: address: {}, station: {}", address, station);
            logger.debug(newFirestation);
        } else {
            logger.error("Cannot update firestation: no firestation \"{}\" in repository with address: {}", station, address);
        }

        return newFirestation;

    }

    @DeleteMapping(path = "/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Firestation delete(@PathVariable("value") final String value) {

        logger.info("Delete request received: delete firestation(s) from repository with address or number: {}", value);

        Firestation firestation = firestationService.delete(value);

        if (firestation != null) {
            if (firestation.getAddress().equals("ALL")) {
                logger.info("Successful delete request: deleted all firestations of number: {}", value);
            } else {
                logger.info("Successful delete request: deleted firestation: {}", firestation);
            }

        } else {
            logger.error("Cannot delete firestation: no station in repository with address or number: {}", value);
        }

        return firestation;
    }


}
