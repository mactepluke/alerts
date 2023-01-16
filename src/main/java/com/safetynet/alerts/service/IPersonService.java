package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;

public interface IPersonService {
    Person create(Person newPerson);

    Person update(String id, Person person);

    Person delete(String id);
}
