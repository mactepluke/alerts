package com.safetynet.alerts.service;


import com.safetynet.alerts.model.*;

import java.text.ParseException;
import java.util.List;

public interface IAdvancedRequestService {
    PersonsByFirestation fetchPersonsByFirestation(String stationNumber) throws ParseException;

    ChildFromAddress fetchChildFromAddress(String address);

    PersonsPhoneByFirestation fetchPersonsPhoneByFirestation(String firestation);

    PersonsAndFirestationFromAddress fetchPersonsAndFirestationFromAddress(String address);

    PersonsByFirestationFlood fetchPersonsByFirestationFlood(List<String> stations);

    PersonInfo fetchPersonInfo(String firstName, String lastName);

    PersonsEmailByCity fetchPersonsEmailByCity(String city);
}
