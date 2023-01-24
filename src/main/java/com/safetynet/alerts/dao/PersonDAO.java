package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * DAO component that handles Person-type objects in the repository
 */
@Component
public class PersonDAO implements IPersonDAO {

    @Autowired
    private DataRepository dataRepository;
    /**
     * @see IPersonDAO#save(Person)
     */
    @Override
    public Person save(Person person) {
        return dataRepository.getPersonsTable().put(person.getId(), person);
    }
    /**
     * @see IPersonDAO#delete(String)
     */
    @Override
    public Person delete(String id) {
        return dataRepository.getPersonsTable().remove(id);
    }
    /**
     * @see IPersonDAO#get(String)
     */
    @Override
    public Person get(String id) {
        return dataRepository.getPersonsTable().get(id);
    }
    /**
     * @see IPersonDAO#getPersonsListByField(FieldType, String)
     */
    @Override
    public List<Person> getPersonsListByField(FieldType fieldType, String fieldValue)   {
        int idCount = 0;
        List<Person> personsList = new ArrayList<>();
        Map<String, Person> personsTable = dataRepository.getPersonsTable();

        switch (fieldType) {
            case ADDRESS -> {
                for (Map.Entry<String, Person> entry : personsTable.entrySet()) {

                    if (Objects.equals(entry.getValue().getAddress(), fieldValue)) {
                        personsList.add(entry.getValue());
                        idCount++;
                    }
                }
            }
            case CITY -> {
                for (Map.Entry<String, Person> entry : personsTable.entrySet()) {

                    if (Objects.equals(entry.getValue().getCity(), fieldValue)) {
                        personsList.add(entry.getValue());
                        idCount++;
                    }
                }
            }
            case LAST_NAME -> {
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
