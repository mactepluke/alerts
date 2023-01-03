package com.safetynet.alerts.model;

import java.util.List;

public interface IFirestation {

    int getStationNumber();

    void addCoveredAddress(String address);

    List<String> getCoveredAddresses();

    void removeCoveredAddress(String address);
}
