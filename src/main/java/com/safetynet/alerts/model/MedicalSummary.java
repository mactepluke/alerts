package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple subtype for handling recurring uses of medical related data
 */
public class MedicalSummary {
    byte age = -1;
    List<String> medications = new ArrayList<>();
    List<String> allergies = new ArrayList<>();

    public MedicalSummary(MedicalRecord medicalRecord) {
        medications.add("No record");
        allergies.add("No record");

        if (medicalRecord != null) {
            age = medicalRecord.getAge();
            medications = medicalRecord.getMedications();
            allergies = medicalRecord.getAllergies();
        }
    }

    public byte getAge() {
        return age;
    }

    public List<String> getMedications() {
        return medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }
}
