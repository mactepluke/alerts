package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLists implements IDataLists    {

    private final List<Person> persons = new ArrayList<>();
    private final List<Firestation> firestations = new ArrayList<>();
    private final List<MedicalRecord> medicalrecords = new ArrayList<>();

    @Override
    public List<Person> getPersonsList() {
        return persons;
    }

    @Override
    public List<Firestation> getFirestationsList() {
        return firestations;
    }

    @Override
    public List<MedicalRecord> getMedicalrecordsList() {
        return medicalrecords;
    }
}
