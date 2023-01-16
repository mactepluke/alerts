package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Person;

public interface IPersonDAO {

    Person save(Person person);

    Person delete(String id);

    Person get(String id);

}
