package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.IPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PersonRepository implements IPersonRepository {

    private static final Logger logger = LoggerFactory.getLogger(PersonRepository.class);

    final Map<Integer, IPerson> personMap;

    public PersonRepository() {
        this.personMap = new HashMap<>();
    }

    @Override
    public void addPerson(IPerson person) {
        this.personMap.put(person.getId(), person);
    }

    @Override
    public IPerson getPerson(int id)    {
        return this.personMap.get(id);
    }

    @Override
    public boolean deletePerson(int id) {
        return this.personMap.remove(id) != null;
    }

}
