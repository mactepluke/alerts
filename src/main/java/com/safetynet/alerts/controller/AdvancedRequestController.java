package com.safetynet.alerts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.service.AdvancedRequestService;
import com.safetynet.alerts.service.DataLists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AdvancedRequestController {

    private static final Logger logger = LogManager.getLogger(AdvancedRequestController.class);

    @Autowired
    AdvancedRequestService ars;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(path = "/firestation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonsByFirestation getPersonsByFirestation(@RequestParam String stationNumber) throws JsonProcessingException {

        logger.info("Get request received: get the list of persons covered by station: {}", stationNumber);

        PersonsByFirestation result = ars.fetchPersonsByFirestation(stationNumber);
        String log = objectMapper.writeValueAsString(result);
        logger.info("Request result: {}", log);

        return result;
    }

    @GetMapping(path = "/childAlert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ChildFromAddress getChildFromAddress(@RequestParam String address) throws JsonProcessingException {

        logger.info("Get request received: get the list of child living at address: {}", address);

        ChildFromAddress result = ars.fetchChildFromAddress(address);
        String log = objectMapper.writeValueAsString(result);
        logger.info("Request result: {}", log);

        return result;
    }

    @GetMapping(path = "/phoneAlert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonsPhoneByFirestation getPersonsPhoneByFirestation(@RequestParam String firestation) {

        logger.info("Get request received: get the list of the phone numbers of persons covered by station: {}", firestation);

        return ars.fetchPersonsPhoneByFirestation(firestation);
    }

    @GetMapping(path = "/fire", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonsAndFirestationFromAddress getPersonsAndFirestationFromAddress(@RequestParam String address) {

        logger.info("Get request received: get the list of persons and the firestation at address: {}", address);

        return ars.fetchPersonsAndFirestationFromAddress(address);
    }

    @GetMapping(path = "/flood/stations", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonsByFirestationFlood getPersonsByFirestationFlood(@RequestParam List<String> stations) {

        logger.info("Get request received: get the list of persons covered by these stations: {}", stations);

        return ars.fetchPersonsByFirestationFlood(stations);
    }

    @GetMapping(path = "/personInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonInfo getPersonInfo(@RequestParam String firstName, @RequestParam String lastName) {

        logger.info("Get request received: get information about: {} {}", firstName, lastName);

        return ars.fetchPersonInfo(firstName, lastName);
    }

    @GetMapping(path = "/communityEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonsEmailByCity getPersonsEmailByCity(@RequestParam String city) {

        logger.info("Get request received: get emails of all persons from: {}", city);

        return ars.fetchPersonsEmailByCity(city);
    }

}
