package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class PersonDAO implements IPersonDAO {

    private static final Logger logger = LogManager.getLogger(PersonDAO.class);

    @Autowired
    private DataRepository dataRepository;

    @Override
    public Person save(Person person) {
        dataRepository.getPersonsTable().put(person.getId(), person);
        return person;
    }

    @Override
    public Person delete(String id) {
        return dataRepository.getPersonsTable().remove(id);
    }

    @Override
    public Person get(String id) {
        return dataRepository.getPersonsTable().get(id);
    }

    public List<Person> getPersonsListByField(String fieldType, String fieldValue)   {
        int idCount = 0;
        List<Person> personsList = new ArrayList<>();
        Map<String, Person> personsTable = dataRepository.getPersonsTable();

        switch (fieldType) {
            case "address" -> {
                for (Map.Entry<String, Person> entry : personsTable.entrySet()) {

                    if (Objects.equals(entry.getValue().getAddress(), fieldValue)) {
                        personsList.add(entry.getValue());
                        idCount++;
                    }
                }
            }
            case "city" -> {
                for (Map.Entry<String, Person> entry : personsTable.entrySet()) {

                    if (Objects.equals(entry.getValue().getCity(), fieldValue)) {
                        personsList.add(entry.getValue());
                        idCount++;
                    }
                }
            }
            case "lastName" -> {
                for (Map.Entry<String, Person> entry : personsTable.entrySet()) {

                    if (Objects.equals(entry.getValue().getLastName(), fieldValue)) {
                        personsList.add(entry.getValue());
                        idCount++;
                    }
                }
            }
        }

        if (idCount == 0)  {
            personsList = null;
        }

        return personsList;
    }

}
