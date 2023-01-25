package com.safetynet.alerts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.service.AdvancedRequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Rest Controller for advanced requests
 */
@RestController
public class AdvancedRequestController {

    private static final Logger logger = LogManager.getLogger(AdvancedRequestController.class);

    @Autowired
    AdvancedRequestService ars;

    @GetMapping(path = "/firestation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonsByFirestation getPersonsByFirestation(@RequestParam String stationNumber) throws JsonProcessingException {

        logger.info("Get request received: get the list of persons covered by station: {}", stationNumber);

        PersonsByFirestation result = ars.fetchPersonsByFirestation(stationNumber);
        logResult(result);

        return result;
    }

    @GetMapping(path = "/childAlert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ChildrenFromAddress getChildrenFromAddress(@RequestParam String address) throws JsonProcessingException {

        logger.info("Get request received: get the list of children living at address: {}", address);

        ChildrenFromAddress result = ars.fetchChildrenFromAddress(address);
        logResult(result);

        return result;
    }

    @GetMapping(path = "/phoneAlert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonsPhoneByFirestation getPersonsPhoneByFirestation(@RequestParam String firestation) throws JsonProcessingException {

        logger.info("Get request received: get the list of the phone numbers of persons covered by station: {}", firestation);

        PersonsPhoneByFirestation result = ars.fetchPersonsPhoneByFirestation(firestation);
        logResult(result);

        return result;
    }

    @GetMapping(path = "/fire", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonsAndFirestationFromAddress getPersonsAndFirestationFromAddress(@RequestParam String address) throws JsonProcessingException {

        logger.info("Get request received: get the list of persons and the firestation at address: {}", address);

        PersonsAndFirestationFromAddress result = ars.fetchPersonsAndFirestationFromAddress(address);
        logResult(result);

        return result;
    }

    @GetMapping(path = "/flood/stations", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonsByFirestationFlood getPersonsByFirestationFlood(@RequestParam List<String> stations) throws JsonProcessingException {

        logger.info("Get request received: get the list of persons covered by these stations: {}", stations);

        PersonsByFirestationFlood result = ars.fetchPersonsByFirestationFlood(stations);
        logResult(result);

        return result;
    }

    @GetMapping(path = "/personInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonInfo getPersonInfo(@RequestParam String firstName, @RequestParam String lastName) throws JsonProcessingException {

        logger.info("Get request received: get information about: {} {}", firstName, lastName);

        PersonInfo result = ars.fetchPersonInfo(firstName, lastName);
        logResult(result);

        return result;
    }

    @GetMapping(path = "/communityEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonsEmailByCity getPersonsEmailByCity(@RequestParam String city) throws JsonProcessingException {

        logger.info("Get request received: get emails of all persons from: {}", city);

        PersonsEmailByCity result = ars.fetchPersonsEmailByCity(city);
        logResult(result);

        return result;
    }

    private static void logResult(Object result) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String log = objectMapper.writeValueAsString(result);
        logger.info("Successful request. Result: {}", log);
    }

}
