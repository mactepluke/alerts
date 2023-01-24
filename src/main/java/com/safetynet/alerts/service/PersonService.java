package com.safetynet.alerts.service;

import com.safetynet.alerts.dao.IPersonDAO;
import com.safetynet.alerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * A service handling model objects of Person type
 */
@Service
public class PersonService implements IPersonService    {

    @Autowired
    private IPersonDAO personDAO;

    /**
     * @see IPersonService#create(Person)
     */
    @Override
    public Person create(Person newPerson) {

        Person result = personDAO.get(newPerson.getId());

        if (result == null) {
            result = personDAO.save(newPerson);
        }
        return result;
    }

    /**
     * @see IPersonService#update(String, Person)
     */
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
    /**
     * @see IPersonService#delete(String)
     */
    @Override
    public Person delete(String id) {

        return personDAO.delete(id);
    }

}
