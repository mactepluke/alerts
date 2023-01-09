package com.safetynet.alerts.model;

import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonIgnore;
import com.jsoniter.annotation.JsonProperty;
import com.jsoniter.annotation.JsonWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Firestation   {

    private static final Logger logger = LogManager.getLogger(Firestation.class);

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

}
