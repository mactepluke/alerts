package com.safetynet.alerts.model;

public class Firestation implements IFirestation    {

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
