package com.safetynet.alerts.service;


import com.safetynet.alerts.model.*;

import java.util.List;

/**
 * Interface for services handling advanced repository searches
 */
public interface IAdvancedRequestService {

    /**
     * This method searches the repository for persons living at addresses covered by a given firestation and creates an object
     * containing names, addresses, phones and the number of adults and children living at those addresses
     * @param stationNumber the station covering a number of persons
     * @return a complex object of PersonsByFirestation type destined to be serialized into a json auto-formatted output
     */
    PersonsByFirestation fetchPersonsByFirestation(String stationNumber);

    /**
     * This method searches the repository for children living at a given address and creates an object
     * containing their names, ages and other home members
     * @param address the address in which to look for children
     * @return a complex object of ChildrenFromAddress type destined to be serialized into a json auto-formatted output
     */
    ChildrenFromAddress fetchChildrenFromAddress(String address);

    /**
     * This method searches the repository for persons covered by a given firestation and creates an object
     * containing their phones
     * @param firestation the firestation covering the persons whose phones are looked for
     * @return a complex object of PersonsPhoneByFirestation type destined to be serialized into a json auto-formatted output
     */
    PersonsPhoneByFirestation fetchPersonsPhoneByFirestation(String firestation);

    /**
     * This method searches the repository for persons living at a given address and creates an object
     * containing their names, phones, age and medical conditions (medications and allergies),
     * as well as the firestation covering them
     * @param address the address where the persons this method looks for are living
     * @return a complex object of PersonsAndFirestationFromAddress type destined to be serialized into a json auto-formatted output
     */
    PersonsAndFirestationFromAddress fetchPersonsAndFirestationFromAddress(String address);

    /**
     * This method searches the repository for all home addresses covered by a list of firestations and creates an object
     * containing a list of persons regrouped by address, as well as the names, phones,
     * ages and medical conditions (medications and allergies) for each of them
     * @param stations a list of stations that are covering the persons whose address this method looks for
     * @return a complex object of PersonsByFirestationFlood type destined to be serialized into a json auto-formatted output
     */
    PersonsByFirestationFlood fetchPersonsByFirestationFlood(List<String> stations);

    /**
     * This method searches the repository for persons of a given last name and creates an object
     * containing their names, addresses, ages, emails and medical conditions (medications and allergies)
     * @param firstName the first name of the first person to look for
     * @param lastName the last name of the persons to look for
     * @return a complex object of PersonInfo type destined to be serialized into a json auto-formatted output
     */
    PersonInfo fetchPersonInfo(String firstName, String lastName);

    /**
     * This method searches the repository for persons living in a given city and creates an object
     * containing their emails
     * @param city the city where live the persons this method looks the emails of
     * @return a complex object of PersonsEmailByCity type destined to be serialized into a json auto-formatted output
     */
    PersonsEmailByCity fetchPersonsEmailByCity(String city);
}
