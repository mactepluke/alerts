package com.safetynet.alerts.model;

import com.jsoniter.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Person {

    private static final Logger logger = LogManager.getLogger(Person.class);


    private final String id;
    private final String firstName;
    private final String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;


    @JsonCreator
    public Person(@JsonProperty(value = "firstName", required = true) String firstName, @JsonProperty(value = "lastName", required = true) String lastName)  {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = firstName+lastName;
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

    public void setAddress(String address)    {
        this.address = address;
    }

    public String getAddress()    {
        return this.address;
    }

    public void setCity(String city)    {
        this.city = city;
    }

    public String getCity()    {
        return this.city;
    }

    public void setZip(String zip)    {
        this.zip = zip;
    }

    public String getZip()    {
        return this.zip;
    }

    public void setPhone(String phone)    {
        this.phone = phone;
    }

    public String getPhone()    {
        return this.phone;
    }

    public void setEmail(String email)    {
        this.email = email;
    }

    public String getEmail()    {
        return this.email;
    }

    @Override
    public String toString()    {
        return "First name: " + this.firstName
                + ", last name: " + this.lastName
                + ", address: " + this.address
                + ", city: " + this.city
                + ", zip: " + this.zip
                + ", phone: " + this.phone
                + ", email: " + this.email;
    }

}
