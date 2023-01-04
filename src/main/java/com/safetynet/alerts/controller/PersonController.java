package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.IPerson;

import com.safetynet.alerts.service.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    IPersonService personService;

    @PostMapping(path = "/person", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public IPerson createPerson(@RequestBody IPerson newPerson) {
        return personService.savePerson(newPerson);

    }

}
