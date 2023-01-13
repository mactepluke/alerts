package com.safetynet.alerts.model;

import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

public class Firestation   {

    private final String address;
    private String station;

    @JsonCreator
    public Firestation(@JsonProperty(value = "address", required = true) String address, @JsonProperty(value = "station", required = true) String station)   {
        this.address = address;
        this.station = station;
    }

    public void setStationNumber(String stationNumber) {
        this.station = stationNumber;
    }

    public String getStationNumber() {
        return this.station;
    }

    public String getAddress()  {
        return this.address;
    }

    @Override
    public String toString() {
        return "address: " + this.address
                + ", station: " + this.station;
    }

}
