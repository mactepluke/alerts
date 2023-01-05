package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.IPerson;
import com.safetynet.alerts.repository.IPersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO implements IPersonDAO    {

    private static final Logger logger = LogManager.getLogger(PersonDAO.class);

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public void savePerson(IPerson person)    {
        personRepository.addPerson(person);
    }

    @Override
    public boolean deletePerson(int id)    {
        return personRepository.deletePerson(id);
    }

    @Override
    public IPerson getPerson(int id)   {
        return personRepository.getPerson(id);
    }
}
