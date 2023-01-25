package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Complex object generated following advanced requests, destined to be serialized into a json auto-formatted output
 */
public class PersonInfo {
    List<Info> personInformation = null;

    public List<Info> getPersonInfo() {
        return personInformation;
    }

    public void addPersonInfo(String firstName, String lastName, String email, MedicalSummary medicalSummary) {
        if (this.personInformation == null) {
            this.personInformation = new ArrayList<>();
        }
        this.personInformation.add(new PersonInfo.Info(firstName, lastName, email, medicalSummary));
    }

    class Info {
        String firstName;
        String lastName;
        byte age;
        String email;
        List<String> medications;
        List<String> allergies;

        public Info(String firstName, String lastName, String email, MedicalSummary medicalSummary) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.age = medicalSummary.getAge();
            this.medications = medicalSummary.getMedications();
            this.allergies = medicalSummary.getMedications();
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

        public String getEmail() {
            return email;
        }

        public List<String> getMedications() {
            return medications;
        }

        public List<String> getAllergies() {
            return allergies;
        }
    }


}
