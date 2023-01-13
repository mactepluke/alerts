package com.safetynet.alerts.controller;

import com.safetynet.alerts.dao.IPersonDAO;
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
@RequestMapping("/person")
public class PersonController {

    private static final Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    private IPersonDAO personDAO;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createPerson(@RequestBody Person newPerson, UriComponentsBuilder uriComponentsBuilder) {

        logger.info("Post request received: create a new person in repository");

        if (isEmpty(newPerson.getFirstName()) || isEmpty(newPerson.getLastName())) {
            logger.error("Invalid post request: empty fields: firstname or lastname");
            return ResponseEntity.badRequest()
                    .body("Bad request: firstname or lastname should not be empty");
        } else {
            logger.info("Successful post request: saving new person in repository with id: {}", newPerson.getId());

            logger.debug(newPerson);
            personDAO.save(newPerson);

            return ResponseEntity
                    .created(uriComponentsBuilder.build(newPerson))
                    .body("Successful post request");
        }

    }

    @PutMapping(path= "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person updatePerson(@PathVariable("id") final String id, @RequestBody Person person) {

        logger.info("Put request received: update a person in repository");

        Optional<Person> e = Optional.ofNullable(personDAO.get(id));

        if(e.isPresent()) {

            Person currentPerson = e.get();

            String address = person.getAddress();
            if(address != null) {
                currentPerson.setAddress(address);
            }

            String city = person.getCity();
            if(city != null) {
                currentPerson.setCity(city);
            }

            String zip = person.getZip();
            if(zip != null) {
                currentPerson.setZip(zip);
            }

            String phone = person.getPhone();
            if(phone != null) {
                currentPerson.setPhone(phone);
            }

            String email = person.getEmail();
            if(email != null) {
                currentPerson.setEmail(email);
            }

            logger.info("Successful put request: saving new person in repository with id: {}", id);
            logger.debug(currentPerson);

            personDAO.save(currentPerson);

            return currentPerson;

        } else {
            logger.error("Cannot update person: no person in repository with id: {}", id);
            return null;
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Person deletePerson(@PathVariable("id") final String id) {

        logger.info("Delete request received: delete a person from repository");

        Person person = personDAO.delete(id);

        if (person != null) {
            logger.info("Successful delete request: deleted person with id: {}", id);
        }
        else {
            logger.error("Cannot delete person: no person in repository with id: {}", id);
        }

        return person;
    }

}
