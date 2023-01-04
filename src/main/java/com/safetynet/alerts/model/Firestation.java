package com.safetynet.alerts.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Firestation implements IFirestation    {

    private static final Logger logger = LoggerFactory.getLogger(Firestation.class);

    private final String address;
    private int number;

    public Firestation(String address, int number)   {
        this.address = address;
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getAddress()  {
        return address;
    }

}
