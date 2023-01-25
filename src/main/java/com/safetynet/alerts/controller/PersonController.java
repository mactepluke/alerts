package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import static org.apache.logging.log4j.util.Strings.isEmpty;

/**
 * Rest Controller for basic create, update and delete requests for persons
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    private IPersonService personService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody Person newPerson, UriComponentsBuilder uriComponentsBuilder) {

        logger.info("Post request received: create a new person in repository");
        Person result;

        if (isEmpty(newPerson.getFirstName()) || isEmpty(newPerson.getLastName())) {
            logger.error("Invalid post request: empty fields: firstname or lastname");
            return ResponseEntity.badRequest()
                    .body("Bad request: firstname or lastname should not be empty");
        } else {

            result = personService.create(newPerson);

            if (result != null) {
                logger.info("Unsuccessful post request: person of id \"{}\" already exists", result.getId());
                return ResponseEntity
                        .created(uriComponentsBuilder.build(newPerson))
                        .body("Unsuccessful post request");
            }
            else {
                logger.info("Successful post request: saving new person in repository with id: {}", newPerson.getId());
                logger.debug(newPerson);
            }

            return ResponseEntity
                    .created(uriComponentsBuilder.build(newPerson))
                    .body("Successful post request");
        }

    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person update(@PathVariable("id") final String id, @RequestBody Person person) {

        logger.info("Put request received: update a person in repository");

        Person newPerson = personService.update(id, person);

        if (newPerson != null) {
            logger.info("Successful put request: saving new person in repository with id: {}", id);
            logger.debug(newPerson);
        } else {
            logger.error("Cannot update person: no person in repository with id: {}", id);
        }

        return newPerson;
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person delete(@PathVariable("id") final String id) {

        logger.info("Delete request received: delete a person from repository");

        Person person = personService.delete(id);

        if (person != null) {
            logger.info("Successful delete request: deleted person with id: {}", id);
            logger.debug(person);
        } else {
            logger.error("Cannot delete person: no person in repository with id: {}", id);
        }

        return person;
    }

}
