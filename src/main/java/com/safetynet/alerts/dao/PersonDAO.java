package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map.Entry;

import static java.lang.System.lineSeparator;

@Component
public class PersonDAO implements IPersonDAO {

    private static final Logger logger = LogManager.getLogger(PersonDAO.class);

    @Autowired
    private DataRepository dataRepository;

    @Override
    public void save(Person person) {
        dataRepository.getPersonsTable().put(person.getId(), person);
    }

    @Override
    public Person delete(String id) {
        return dataRepository.getPersonsTable().remove(id);
    }

    @Override
    public Person get(String id) {
        return dataRepository.getPersonsTable().get(id);
    }

}
