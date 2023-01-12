package com.safetynet.alerts.dao;

import org.springframework.stereotype.Component;
import com.safetynet.alerts.model.Person;

@Component
public interface IPersonDAO {

    void save(Person person);

    Person delete(String id);

    Person get(String id);

}
