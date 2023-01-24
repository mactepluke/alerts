package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Component that temporarily stores the lists of the person, firestation and medical record data retrieved by "JsonIterator"
 */
@Component
public class DataLists implements IDataLists    {

    private final List<Person> persons = new ArrayList<>();
    private final List<Firestation> firestations = new ArrayList<>();
    private final List<MedicalRecord> medicalrecords = new ArrayList<>();

    /**
     * @see IDataLists#getPersonsList()
     */
    @Override
    public List<Person> getPersonsList() {
        return persons;
    }
    /**
     * @see IDataLists#getFirestationsList()
     */
    @Override
    public List<Firestation> getFirestationsList() {
        return firestations;
    }
    /**
     * @see IDataLists#getMedicalrecordsList()
     */
    @Override
    public List<MedicalRecord> getMedicalrecordsList() {
        return medicalrecords;
    }
}
