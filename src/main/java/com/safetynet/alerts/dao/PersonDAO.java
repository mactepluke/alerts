package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class PersonDAO implements IPersonDAO {

    private static final Logger logger = LogManager.getLogger(PersonDAO.class);
    private final Map<String, Person> personsRepository = new HashMap<>();

    @Override
    public void savePerson(Person person) {
        personsRepository.put(person.getId(), person);
    }

    @Override
    public Person deletePerson(String id) {
        return personsRepository.remove(id);
    }

    @Override
    public Person getPerson(String id) {
        return personsRepository.get(id);
    }

    @Override
    public Map<String, Person> getPersonsTable()  {
        return personsRepository;
    }

}
