package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;

public class Firestation implements IFirestation    {

    private final int stationNumber;
    private final List<String> coveredAddresses;

    public Firestation(int stationNumber) {
        this.stationNumber = stationNumber;
        this.coveredAddresses = new ArrayList<>();
    }

    @Override
    public int getStationNumber()    {
        return this.stationNumber;
    }

    @Override
    public void addCoveredAddress(String address)    {
        this.coveredAddresses.add(address);
    }

    @Override
    public List<String> getCoveredAddresses()    {
        return this.coveredAddresses;
    }

    @Override
    public void removeCoveredAddress(String address)    {
        this.coveredAddresses.remove(address);
    }
}
