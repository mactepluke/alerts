package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
public interface IFirestationService {


    Firestation create(Firestation newFirestation);

    Firestation update(String address, String station);

    Firestation delete(String value);
}
