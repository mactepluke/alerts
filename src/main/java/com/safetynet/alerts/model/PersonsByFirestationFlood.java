package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;

public class PersonsByFirestationFlood {
    List<CoveredHome> coveredHomes = null;

    public void addCoveredPerson(String station, String address, String firstName, String lastName, MedicalSummary medicalSummary)  {
        if (coveredHomes == null)   {
            coveredHomes = new ArrayList<>();
        }

        this.coveredHomes.add(new CoveredHome(station, address, firstName, lastName, medicalSummary));
    }

    public List<CoveredHome> getCoveredHomes() {
        return coveredHomes;
    }

    class CoveredHome {
        String station;
        List<Home> homes = new ArrayList<>();

        public CoveredHome(String station, String address, String firstName, String lastName, MedicalSummary medicalSummary)  {
            this.station = station;
            this.homes.add(new Home(address, firstName, lastName, medicalSummary));
        }

        public String getStation() {
            return station;
        }

        public List<Home> getHomes() {
            return homes;
        }

        class Home  {
            String address;
            List<CoveredPerson> coveredPersonList = new ArrayList<>();

            public Home(String address, String firstName, String lastName, MedicalSummary medicalSummary)    {
                this.address = address;
                this.coveredPersonList.add(new CoveredPerson(firstName, lastName, medicalSummary));
            }

            public String getAddress() {
                return address;
            }

            public List<CoveredPerson> getCoveredPersonList() {
                return coveredPersonList;
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
    }

}
