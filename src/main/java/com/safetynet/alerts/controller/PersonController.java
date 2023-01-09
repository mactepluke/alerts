package com.safetynet.alerts.controller;

import com.safetynet.alerts.dao.IPersonDAO;
import com.safetynet.alerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
public class PersonController {

    private static final Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    IPersonDAO personDAO;

    @PostMapping(path = "/person", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void createPerson(@RequestBody Person newPerson) {

        logger.trace(newPerson.getFirstName());
        logger.trace(newPerson.getLastName());
        logger.trace(newPerson.getAddress());
        logger.trace(newPerson.getCity());
        logger.trace(newPerson.getZip());
        logger.trace(newPerson.getPhone());
        logger.trace(newPerson.getEmail());

        if (isEmpty(newPerson.getFirstName()) || isEmpty(newPerson.getLastName()))  {
            logger.error("Invalid post request: empty fields: firstname or lastname");
        }   else {
            logger.info("Successful post request: saving new person in repository with ID: {}", newPerson.getId());
            personDAO.savePerson(newPerson);
        }

    }

    @GetMapping("/person/{id}")
    public Person getEmployee(@PathVariable("id") final String id) {
        return personDAO.getPerson(id);

    }

}
