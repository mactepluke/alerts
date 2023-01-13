package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.lineSeparator;

@Repository
public class DataRepository {
    private Map<String, Person> personsTable = new HashMap<>();
    private Map<String, MedicalRecord> medicalRecordsTable = new HashMap<>();
    private Map<String, String> firestationsTable = new HashMap<>();

    public Map<String, Person> getPersonsTable() {
        return this.personsTable;
    }

    public Map<String, MedicalRecord> getMedicalRecordsTable() {
        return this.medicalRecordsTable;
    }

    public Map<String, String> getFirestationsTable() {
        return this.firestationsTable;
    }
    public void resetTables() {
        this.personsTable = new HashMap<>();
        this.medicalRecordsTable = new HashMap<>();
        this.firestationsTable = new HashMap<>();
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
