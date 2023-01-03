package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.IPerson;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository {

    void addPerson(IPerson person);

    IPerson getPerson(int id);

    boolean deletePerson(int id);

}
