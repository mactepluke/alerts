package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

import java.util.List;

public interface IDataLists {
    List<Person> getPersonsList();

    List<Firestation> getFirestationsList();

    List<MedicalRecord> getMedicalrecordsList();
}
