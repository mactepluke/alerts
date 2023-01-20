package com.safetynet.alerts.model;

import java.util.*;

public class PersonsByFirestationFlood {

    Map<String, Map<String, Set<CoveredPerson>>> stations = null;


    public void addCoveredPerson(String station, String address, String firstName, String lastName, MedicalSummary medicalSummary)  {
        CoveredPerson coveredPerson = new CoveredPerson(firstName, lastName, medicalSummary);

        if (this.stations == null) {
            this.stations = new HashMap<>();
        }

        this.stations.computeIfAbsent(station, k -> new HashMap<>());
        this.stations.get(station).computeIfAbsent(address, k -> new HashSet<>());
        this.stations.get(station).get(address).add(coveredPerson);
    }

    public Map<String, Map<String, Set<CoveredPerson>>> getStations() {
        return stations;
    }

    class CoveredPerson {
        String firstName;
        String lastName;
        byte age;
        List<String> medications;
        List<String> allergies;

        public CoveredPerson(String firstName, String lastName, MedicalSummary medicalSummary) {
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
