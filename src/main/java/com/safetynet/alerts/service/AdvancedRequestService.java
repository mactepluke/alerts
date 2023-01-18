package com.safetynet.alerts.service;

import com.safetynet.alerts.dao.FirestationDAO;
import com.safetynet.alerts.dao.MedicalRecordDAO;
import com.safetynet.alerts.dao.PersonDAO;
import com.safetynet.alerts.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvancedRequestService implements IAdvancedRequestService {

    private static final Logger logger = LogManager.getLogger(AdvancedRequestService.class);

    @Autowired
    PersonDAO personDAO;

    @Autowired
    MedicalRecordDAO medicalRecordDAO;

    @Autowired
    FirestationDAO firestationDAO;

    @Override
    public PersonsByFirestation fetchPersonsByFirestation(String stationNumber) {
        PersonsByFirestation personsByFirestation = new PersonsByFirestation();
        List<String> addressList = firestationDAO.getAddresses(stationNumber);

        if (addressList != null) {
            int childNumber = 0;
            int adultNumber = 0;
            int unknownNumber = 0;
            List<Person> persons;

            for (String address : addressList) {
                persons = personDAO.getPersonsListByField("address", address);
                for (Person person : persons) {
                    personsByFirestation.addCoveredPerson(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone());

                    MedicalRecord medicalRecord = medicalRecordDAO.get(person.getId());

                    if (medicalRecord != null) {
                        if (medicalRecord.isAChild()) {
                            childNumber++;
                        } else {
                            adultNumber++;
                        }
                    }
                    else {
                        unknownNumber++;
                    }
                }
            }
            personsByFirestation.setChild(childNumber);
            personsByFirestation.setAdults(adultNumber);
            personsByFirestation.setUnknown(unknownNumber);
        }

        return personsByFirestation;
    }

    @Override
    public ChildFromAddress fetchChildFromAddress(String address) {
        ChildFromAddress childFromAddress = new ChildFromAddress();
        List<Person> others = null;

        List<Person> personsInHome = personDAO.getPersonsListByField("address", address);

        if (personsInHome != null)  {
            for (Person person : personsInHome) {

                MedicalRecord medicalRecord = medicalRecordDAO.get(person.getId());
                if (medicalRecord != null && medicalRecord.isAChild())  {
                    childFromAddress.addChild(person.getFirstName(), person.getLastName(), medicalRecord.getAge());
                }   else {
                    if (others == null) {
                        others = new ArrayList<>();
                    }
                    others.add(person);
                }
            }
        }

        if (childFromAddress.getChild() != null) {
            childFromAddress.setOthers(others);
        } else {
            childFromAddress = null;
        }

        return childFromAddress;
    }

    @Override
    public PersonsPhoneByFirestation fetchPersonsPhoneByFirestation(String firestation) {
        PersonsPhoneByFirestation personsPhoneByFirestation = new PersonsPhoneByFirestation();
        //TODO
        return personsPhoneByFirestation;
    }

    @Override
    public PersonsAndFirestationFromAddress fetchPersonsAndFirestationFromAddress(String address) {
        PersonsAndFirestationFromAddress personsAndFirestationFromAddress = new PersonsAndFirestationFromAddress();
        //TODO
        return personsAndFirestationFromAddress;
    }

    @Override
    public PersonsByFirestationFlood fetchPersonsByFirestationFlood(List<String> stations) {
        PersonsByFirestationFlood personsByFirestationFlood = new PersonsByFirestationFlood();
        //TODO
        return personsByFirestationFlood;
    }

    @Override
    public PersonInfo fetchPersonInfo(String firstName, String lastName) {
        PersonInfo personInfo = new PersonInfo();
        //TODO
        return personInfo;
    }

    @Override
    public PersonsEmailByCity fetchPersonsEmailByCity(String city) {
        PersonsEmailByCity personsEmailByCity = new PersonsEmailByCity();
        //TODO
        return personsEmailByCity;
    }


}
