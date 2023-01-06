package com.safetynet.alerts.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FirestationRepository implements IFirestationRepository    {

    private static final Logger logger = LogManager.getLogger(FirestationRepository.class);

    private final Map<String, String> firestationMap = new HashMap<>();

    @Override
    public void putFirestation(String address, String firestationNumber)    {
        this.firestationMap.put(address, firestationNumber);
    }

    @Override
    public String getFirestationNumber(String address)    {
        return this.firestationMap.get(address);
    }

    @Override
    public boolean deleteFirestation(String address)    {
        return this.firestationMap.remove(address) != null;
    }

}
