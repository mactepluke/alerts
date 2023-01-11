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
    IPersonDAO personDAO;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPerson(@RequestBody Person newPerson, UriComponentsBuilder uriComponentsBuilder) {

        if (isEmpty(newPerson.getFirstName()) || isEmpty(newPerson.getLastName())) {
            logger.error("Invalid post request: empty fields: firstname or lastname");
            return ResponseEntity.badRequest()
                    .body("400 Bad request: firstname or lastname should not be empty");
        } else {
            logger.info("Successful post request: saving new person in repository with id: {}", newPerson.getId());
            logger.debug(newPerson.toString());

            personDAO.savePerson(newPerson);

            return ResponseEntity
                    .created(uriComponentsBuilder.build(newPerson))
                    .body("201 Successful post request");
        }

    }

    @PutMapping("{id}")
    public Person updateEmployee(@PathVariable("id") final String id, @RequestBody Person person) {
        Optional<Person> e = Optional.ofNullable(personDAO.getPerson(id));

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

            logger.info("Successful put request: saving new person in repository with id: {}", currentPerson.getId());
            logger.debug(currentPerson.toString());

            personDAO.savePerson(currentPerson);

            return currentPerson;

        } else {

            return null;
        }
    }

}
