package com.safetynet.alerts.model;

import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord  {

    private final String id;
    private final String firstName;
    private final String lastName;
    private String birthdate;
    private final List<String> medications;
    private final List<String> allergies;

    @JsonCreator
    public MedicalRecord(@JsonProperty(value = "firstName", required = true) String firstName, @JsonProperty(value = "lastName", required = true) String lastName)  {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = firstName+lastName;
        this.medications = new ArrayList<>();
        this.allergies = new ArrayList<>();
    }

    public String getId()    {
        return this.id;
    }

    public String getFirstName()    {
        return this.firstName;
    }

    public String getLastName()    {
        return this.lastName;
    }

    public void setBirthdate(String birthdate)    {
        this.birthdate = birthdate;
    }

    public String getBirthdate()    {
        return this.birthdate;
    }

    public void addMedication(String medication)    {
        this.medications.add(medication);
    }

    public List<String> getMedications()    {
        return this.medications;
    }

    public void deleteMedication(String medication)    {
        this.medications.remove(medication);
    }

    public void addAllergy(String allergy)    {
        this.allergies.add(allergy);
    }

    public List<String> getAllergies()    {
        return this.allergies;
    }

    public void removeAllergy(String allergy)    {
        this.allergies.remove(allergy);
    }

    @Override
    public String toString()    {
        return "first name: " + this.firstName
                + ", last name: " + this.lastName
                + ", birthdate: " + this.birthdate
                + ", medications: " + this.medications
                + ", allergies: " + this.allergies;
    }

}
