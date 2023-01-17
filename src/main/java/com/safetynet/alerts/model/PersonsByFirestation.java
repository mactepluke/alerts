package com.safetynet.alerts.model;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PersonsByFirestation {
    private int adultNumber = 0;
    private int childNumber = 0;
    private List<CoveredPerson> coveredPersonsList = null;

    public int getAdultNumber() {
        return adultNumber;
    }

    public int getChildNumber() {
        return childNumber;
    }

    private static final Logger logger = LogManager.getLogger(PersonsByFirestation.class);

    public void setAdultNumber(int adultNumber) {
        this.adultNumber = adultNumber;
    }

    public void setChildNumber(int childNumber) {
        this.childNumber = childNumber;
    }

    public void addCoveredPerson(String firstName, String lastName, String address, String phone) {
        if (this.coveredPersonsList == null) {
            this.coveredPersonsList = new ArrayList<>();
        }
        this.coveredPersonsList.add(new CoveredPerson(firstName, lastName, address, phone));
    }

    public List<CoveredPerson> getCoveredPersonsList() {
        return this.coveredPersonsList;
    }

    class CoveredPerson {
        String firstName;
        String lastName;
        String address;
        String phone;

        public CoveredPerson(String firstName, String lastName, String address, String phone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.phone = phone;
        }

            public String getFirstName() {
                return this.firstName;
            }

            public String getLastName() {
                return this.lastName;
            }

            public String getAddress() {
                return this.address;
            }

            public String getPhone() {
                return this.phone;
            }
        }
    }


