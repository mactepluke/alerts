package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

import java.util.*;

import static java.lang.System.lineSeparator;


public class DataRepository {
    private Map<String, Person> personsTable = new HashMap<>();
    private Map<String, MedicalRecord> medicalRecordsTable = new HashMap<>();
    private Map<String, String> firestationsTable = new HashMap<>();

    public void setPersonsTable(Map<String, Person> personsTable) {
        this.personsTable = personsTable;
    }

    public void setMedicalRecordsTable(Map<String, MedicalRecord> medicalRecordsTable) {
        this.medicalRecordsTable = medicalRecordsTable;
    }

    public void setFirestationsTable(Map<String, String> firestationsTable) {
        this.firestationsTable = firestationsTable;
    }

    public Map<String, Person> getPersonsTable() {
        return this.personsTable;
    }

    public Map<String, MedicalRecord> getMedicalRecordsTable() {
        return this.medicalRecordsTable;
    }

    public Map<String, String> getFirestationsTable() {
        return this.firestationsTable;
    }

    public void copyRepository(DataRepository dataRepository) {
        this.personsTable = dataRepository.getPersonsTable();
        this.medicalRecordsTable = dataRepository.getMedicalRecordsTable();
        this.firestationsTable = dataRepository.getFirestationsTable();
    }


    @Override
    public String toString() {
        StringBuilder stringRepository = new StringBuilder();

        stringRepository.append(lineSeparator());
        stringRepository.append(lineSeparator());
        stringRepository.append("[PERSONS]");
        stringRepository.append(lineSeparator());
        stringRepository.append(lineSeparator());
        for (Map.Entry<String, Person> entry : personsTable.entrySet()) {
            stringRepository.append(entry.getValue()).append(lineSeparator());
        }

        stringRepository.append(lineSeparator());
        stringRepository.append("[MEDICAL RECORDS]");
        stringRepository.append(lineSeparator());
        stringRepository.append(lineSeparator());

        for (Map.Entry<String, MedicalRecord> entry : medicalRecordsTable.entrySet()) {
            stringRepository.append(entry.getValue()).append(lineSeparator());
        }

        stringRepository.append(lineSeparator());
        stringRepository.append("[FIRESTATIONS]");
        stringRepository.append(lineSeparator());
        stringRepository.append(lineSeparator());

        for (Map.Entry<String, String> entry : firestationsTable.entrySet()) {
            Firestation firestation = new Firestation(entry.getKey(), entry.getValue());
            stringRepository.append(firestation).append(lineSeparator());
        }

        stringRepository.append(lineSeparator());

        return stringRepository.toString();
    }
}
