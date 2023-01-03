package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord implements IMedicalRecord    {

    private final int id;
    private final String firstName;
    private final String lastName;
    private String birthdate;
    private final List<String> medications;
    private final List<String> allergies;

    public MedicalRecord(String firstName, String lastName)  {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = (firstName+lastName).hashCode();
        this.medications = new ArrayList<>();
        this.allergies = new ArrayList<>();
    }

    @Override
    public int getId()    {
        return this.id;
    }

    @Override
    public String getFirstName()    {
        return this.firstName;
    }

    @Override
    public String getLastName()    {
        return this.lastName;
    }

    @Override
    public void setBirthdate(String birthdate)    {
        this.birthdate = birthdate;
    }

    @Override
    public String getBirthdate()    {
        return this.birthdate;
    }

    @Override
    public void addMedication(String medication)    {
        this.medications.add(medication);
    }

    @Override
    public List<String> getMedications()    {
        return this.medications;
    }

    @Override
    public void deleteMedication(String medication)    {
        this.medications.remove(medication);
    }

    @Override
    public void addAllergy(String allergy)    {
        this.allergies.add(allergy);
    }

    @Override
    public List<String> getAllergies()    {
        return this.allergies;
    }

    @Override
    public void removeAllergy(String allergy)    {
        this.allergies.remove(allergy);
    }

}
