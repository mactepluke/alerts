package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Complex object generated following advanced requests, destined to be serialized into a json auto-formatted output
 */
public class PersonsByFirestation {
    private int adults = 0;
    private int child = 0;
    private int unknown = 0;
    private List<CoveredPerson> coveredPersonsList = null;

    public int getAdults() {
        return adults;
    }

    public int getChild() {
        return child;
    }

    public int getUnknown() {
        return unknown;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public void setUnknown(int unknown) {
        this.unknown = unknown;
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


