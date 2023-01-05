package com.safetynet.alerts.service;

import com.safetynet.alerts.dao.IPersonDAO;
import com.safetynet.alerts.model.IPerson;
import com.safetynet.alerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService    {

    private static final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    IPersonDAO personDAO;

    @Override
    public IPerson savePerson(IPerson person) {
        personDAO.savePerson(person);

        return person;
    }

    public IPerson getPerson(int id)   {
        return personDAO.getPerson(id);
    }
}
