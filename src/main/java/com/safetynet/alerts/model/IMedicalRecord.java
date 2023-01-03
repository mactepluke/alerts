package com.safetynet.alerts.model;

import java.util.List;

public interface IMedicalRecord {

    int getId();

    String getFirstName();

    String getLastName();

    void setBirthdate(String birthdate);

    String getBirthdate();

    void addMedication(String medication);

    List<String> getMedications();

    void deleteMedication(String medication);

    void addAllergy(String allergy);

    List<String> getAllergies();

    void removeAllergy(String allergy);

}
