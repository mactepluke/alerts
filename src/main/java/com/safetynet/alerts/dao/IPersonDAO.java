package com.safetynet.alerts.dao;

import org.springframework.stereotype.Component;
import com.safetynet.alerts.model.Person;

import java.util.Map;

@Component
public interface IPersonDAO {

    void savePerson(Person person);

    Person deletePerson(String id);

    Person getPerson(String id);

    Map<String, Person> getPersonsTable();

}
