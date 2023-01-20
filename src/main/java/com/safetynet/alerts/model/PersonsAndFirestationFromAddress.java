package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;

public class PersonsAndFirestationFromAddress {
    private String station = "<no station is covering these addresses>";
    private List<PersonsLivingAtAddress> personsLivingAtAddress = null;

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public List<PersonsLivingAtAddress> getPersonsLivingAtAddress() {
        return personsLivingAtAddress;
    }

    public void addPersonsLivingAtAddress(String firstName, String lastName, MedicalSummary medicalSummary) {
        if (this.personsLivingAtAddress == null) {
            this.personsLivingAtAddress = new ArrayList<>();
        }
        this.personsLivingAtAddress.add(new PersonsAndFirestationFromAddress.PersonsLivingAtAddress(firstName, lastName, medicalSummary));
    }

    class PersonsLivingAtAddress  {
        String firstName;
        String lastName;
        byte age;
        List<String> medications;
        List<String> allergies;

        public PersonsLivingAtAddress(String firstName, String lastName, MedicalSummary medicalSummary) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = medicalSummary.getAge();
            this.medications = medicalSummary.getMedications();
            this.allergies = medicalSummary.getAllergies();
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
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


}
