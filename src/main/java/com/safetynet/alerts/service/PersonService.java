package com.safetynet.alerts.service;

import com.safetynet.alerts.controller.MedicalRecordController;
import com.safetynet.alerts.dao.IPersonDAO;
import com.safetynet.alerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PersonService implements IPersonService    {

    private static final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    private IPersonDAO personDAO;

    @Override
    public Person create(Person newPerson) {

        return personDAO.save(newPerson);

    }

    @Override
    public Person update(String id, Person person) {

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

            personDAO.save(currentPerson);

            return currentPerson;

        } else {
            return null;
        }
    }

    @Override
    public Person delete(String id) {

        return personDAO.delete(id);
    }

}
