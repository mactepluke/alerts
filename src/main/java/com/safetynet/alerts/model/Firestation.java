package com.safetynet.alerts.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Firestation implements IFirestation    {

    private static final Logger logger = LogManager.getLogger(Firestation.class);

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
