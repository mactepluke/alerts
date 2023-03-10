package com.safetynet.alerts.service;

import com.safetynet.alerts.dao.FirestationDAO;
import com.safetynet.alerts.dao.MedicalRecordDAO;
import com.safetynet.alerts.dao.PersonDAO;
import com.safetynet.alerts.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.safetynet.alerts.dao.IPersonDAO.FieldType.*;

/**
 * Service that handles advanced repository searches
 */
@Service
public class AdvancedRequestService implements IAdvancedRequestService {

    @Autowired
    PersonDAO personDAO;

    @Autowired
    MedicalRecordDAO medicalRecordDAO;

    @Autowired
    FirestationDAO firestationDAO;

    /**
     * @see IAdvancedRequestService#fetchPersonsByFirestation(String)
     */
    @Override
    public PersonsByFirestation fetchPersonsByFirestation(String stationNumber) {
        PersonsByFirestation personsByFirestation = null;
        List<String> addressList = firestationDAO.getAddresses(stationNumber);

        if (addressList != null) {
            int childNumber = 0;
            int adultNumber = 0;
            int unknownNumber = 0;
            List<Person> persons;

            for (String address : addressList) {
                persons = personDAO.getPersonsListByField(ADDRESS, address);
                for (Person person : persons) {
                    if (personsByFirestation == null) {
                        personsByFirestation = new PersonsByFirestation();
                    }
                    personsByFirestation.addCoveredPerson(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone());

                    MedicalRecord medicalRecord = medicalRecordDAO.get(person.getId());

                    if (medicalRecord != null) {
                        if (medicalRecord.isAChild()) {
                            childNumber++;
                        } else {
                            adultNumber++;
                        }
                    } else {
                        unknownNumber++;
                    }
                }
            }
            if (personsByFirestation != null) {
                personsByFirestation.setChild(childNumber);
                personsByFirestation.setAdults(adultNumber);
                personsByFirestation.setUnknown(unknownNumber);
            }
        }

        return personsByFirestation;
    }

    /**
     * @see IAdvancedRequestService#fetchChildrenFromAddress(String)
     */
    @Override
    public ChildrenFromAddress fetchChildrenFromAddress(String address) {
        ChildrenFromAddress childrenFromAddress = new ChildrenFromAddress();
        List<Person> others = null;

        List<Person> personsInHome = personDAO.getPersonsListByField(ADDRESS, address);

        if (personsInHome != null) {
            for (Person person : personsInHome) {

                MedicalRecord medicalRecord = medicalRecordDAO.get(person.getId());
                if (medicalRecord != null && medicalRecord.isAChild()) {
                    childrenFromAddress.addChild(person.getFirstName(), person.getLastName(), medicalRecord.getAge());
                } else {
                    if (others == null) {
                        others = new ArrayList<>();
                    }
                    others.add(person);
                }
            }
        }

        if (childrenFromAddress.getChild() != null) {
            childrenFromAddress.setOthers(others);
        } else {
            childrenFromAddress = null;
        }

        return childrenFromAddress;
    }

    /**
     * @see IAdvancedRequestService#fetchPersonsPhoneByFirestation(String)
     */
    @Override
    public PersonsPhoneByFirestation fetchPersonsPhoneByFirestation(String firestation) {
        PersonsPhoneByFirestation personsPhoneByFirestation = null;
        List<String> addresses;
        List<Person> personsList = null;

        addresses = firestationDAO.getAddresses(firestation);

        if (addresses != null) {

            for (String address : addresses) {
                List<Person> homeResidents = personDAO.getPersonsListByField(ADDRESS, address);

                if (homeResidents != null) {
                    if (personsList == null) {
                        personsList = new ArrayList<>();
                    }
                    personsList.addAll(homeResidents);
                }
            }

            if (personsList != null) {
                Set<String> phones = new HashSet<>();
                for (Person person : personsList) {
                    String phone = person.getPhone();
                    if (phone != null) {
                        phones.add(phone);
                    }
                }

                personsPhoneByFirestation = new PersonsPhoneByFirestation();
                personsPhoneByFirestation.setPhones(phones);
            }
        }

        return personsPhoneByFirestation;
    }

    /**
     * @see IAdvancedRequestService#fetchPersonsAndFirestationFromAddress(String)
     */
    @Override
    public PersonsAndFirestationFromAddress fetchPersonsAndFirestationFromAddress(String address) {
        PersonsAndFirestationFromAddress personsAndFirestationFromAddress = null;

        List<Person> personList = personDAO.getPersonsListByField(ADDRESS, address);

        if (personList != null) {
            personsAndFirestationFromAddress = new PersonsAndFirestationFromAddress();

            for (Person person : personList) {

                MedicalSummary medicalSummary = new MedicalSummary(medicalRecordDAO.get(person.getId()));

                personsAndFirestationFromAddress.addPersonsLivingAtAddress(person.getFirstName(), person.getLastName(), medicalSummary);
            }
        }

        String station = firestationDAO.get(address);
        if (station != null) {
            if (personsAndFirestationFromAddress == null) {
                personsAndFirestationFromAddress = new PersonsAndFirestationFromAddress();
            }
            personsAndFirestationFromAddress.setStation(station);
        }

        return personsAndFirestationFromAddress;
    }

    /**
     * @see IAdvancedRequestService#fetchPersonsByFirestationFlood(List<String>)
     */
    @Override
    public PersonsByFirestationFlood fetchPersonsByFirestationFlood(List<String> stations) {
        PersonsByFirestationFlood personsByFirestationFlood = null;

        if (stations != null) {

            for (String station : stations) {

                List<String> addresses = firestationDAO.getAddresses(station);

                if (addresses != null) {

                    for (String address : addresses) {
                        List<Person> personList = personDAO.getPersonsListByField(ADDRESS, address);

                        if (personList != null) {
                            for (Person person : personList) {

                                MedicalSummary medicalSummary = new MedicalSummary(medicalRecordDAO.get(person.getId()));

                                if (personsByFirestationFlood == null) {
                                    personsByFirestationFlood = new PersonsByFirestationFlood();
                                }

                                personsByFirestationFlood.addCoveredPerson(station, address, person.getFirstName(), person.getLastName(), person.getPhone(), medicalSummary);
                            }
                        }
                    }
                }
            }
        }

        return personsByFirestationFlood;
    }

    /**
     * @see IAdvancedRequestService#fetchPersonInfo(String, String)
     */
    @Override
    public PersonInfo fetchPersonInfo(String firstName, String lastName) {
        PersonInfo personInfo = null;

        List<Person> personList = personDAO.getPersonsListByField(LAST_NAME, lastName);

        if (personList != null) {
            personInfo = new PersonInfo();

            for (Person person : personList) {

                MedicalSummary medicalSummary = new MedicalSummary(medicalRecordDAO.get(person.getId()));

                personInfo.addPersonInfo(person.getFirstName(), lastName, person.getEmail(), medicalSummary);
            }
        }
        return personInfo;
    }

    /**
     * @see IAdvancedRequestService#fetchPersonsEmailByCity(String)
     */
    @Override
    public PersonsEmailByCity fetchPersonsEmailByCity(String city) {
        PersonsEmailByCity personsEmailByCity = null;

        List<Person> personList = personDAO.getPersonsListByField(CITY, city);
        if (personList != null) {
            personsEmailByCity = new PersonsEmailByCity();
            Set<String> emails = new HashSet<>();

            for (Person person : personList) {
                String email = person.getEmail();
                if (email != null) {
                    emails.add(email);
                }
            }
            personsEmailByCity.setEmails(emails);
        }

        return personsEmailByCity;
    }

}
