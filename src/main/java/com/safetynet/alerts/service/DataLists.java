package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLists {
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<MedicalRecord> medicalrecords;

    public List<Person> getPersonsList() {
        return persons;
    }

    public List<Firestation> getFirestationsList() {
        return firestations;
    }

    public List<MedicalRecord> getMedicalrecordsList() {
        return medicalrecords;
    }
}
