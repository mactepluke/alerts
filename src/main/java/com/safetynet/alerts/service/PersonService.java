package com.safetynet.alerts.service;

import com.safetynet.alerts.dao.IPersonDAO;
import com.safetynet.alerts.model.IPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService    {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    IPersonDAO personDAO;

    @Override
    public IPerson savePerson(IPerson person) {
        return person;
    }
}
