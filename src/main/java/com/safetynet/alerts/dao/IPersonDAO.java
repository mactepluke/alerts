package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.IPerson;
import org.springframework.stereotype.Component;

@Component
public interface IPersonDAO {

    void savePerson(IPerson person);

    boolean deletePerson(int id);

    IPerson getPerson(int id);
}
