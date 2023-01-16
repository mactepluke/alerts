package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvancedRequestController {

    private static final Logger logger = LogManager.getLogger(AdvancedRequestController.class);

    @GetMapping(path = "/firestation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Person> getPersonsByFirestation(@RequestParam String stationNumber) {

        logger.info("Get request received: get the list of persons covered by station: {}", stationNumber);
        /* Optional<String> address = firestationDAO.get(station);

        if(firestation.isPresent()) {
            return firestation.get();
        } else {*/
        return null;
        //}
    }

    @GetMapping(path = "/childAlert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Person> getChildFromAddress(@RequestParam String address) {

        logger.info("Get request received: get the list of child living at address: {}", address);

        return null;
    }

    @GetMapping(path = "/phoneAlert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Person> getPersonsPhoneByFirestation(@RequestParam String firestation) {

        logger.info("Get request received: get the list of the phone numbers of persons covered by station: {}", firestation);

        return null;
    }

    @GetMapping(path = "/fire", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Person> getPersonsAndFirestationFromAddress(@RequestParam String address) {

        logger.info("Get request received: get the list of persons and the firestation at address: {}", address);

        return null;
    }

    @GetMapping(path = "/flood/stations", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Person> getPersonsByFirestation(@RequestParam List<String> stations) {

        logger.info("Get request received: get the list of persons covered by these stations: {}", stations);

        return null;
    }

    @GetMapping(path = "/personInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Person> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName) {

        logger.info("Get information about: {} {}", firstName, lastName);

        return null;
    }

    @GetMapping(path = "/communityEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Person> getPersonsEmailByCity(@RequestParam String city) {

        logger.info("Get emails of all persons from: {}", city);

        return null;
    }

}
