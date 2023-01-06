package com.safetynet.alerts.model;

import com.jsoniter.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Person implements IPerson  {

    private static final Logger logger = LogManager.getLogger(Person.class);


    private final int id;
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
        this.id = (firstName+lastName).hashCode();
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
    public void setAddress(String address)    {
        this.address = address;
    }

    @Override
    public String getAddress()    {
        return this.address;
    }

    @Override
    public void setCity(String city)    {
        this.city = city;
    }

    @Override
    public String getCity()    {
        return this.city;
    }

    @Override
    public void setZip(String zip)    {
        this.zip = zip;
    }

    @Override
    public String getZip()    {
        return this.zip;
    }

    @Override
    public void setPhone(String phone)    {
        this.phone = phone;
    }

    @Override
    public String getPhone()    {
        return this.phone;
    }

    @Override
    public void setEmail(String email)    {
        this.email = email;
    }

    @Override
    public String getEmail()    {
        return this.email;
    }

}
