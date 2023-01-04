package com.safetynet.alerts.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FirestationRepository implements IFirestationRepository    {

    private static final Logger logger = LoggerFactory.getLogger(FirestationRepository.class);

    private final Map<String, Integer> firestationMap = new HashMap<>();

    @Override
    public void putFirestation(String address, int firestationNumber)    {
        this.firestationMap.put(address, firestationNumber);
    }

    @Override
    public int getFirestationNumber(String address)    {
        return this.firestationMap.get(address);
    }

    @Override
    public boolean deleteFirestation(String address)    {
        return this.firestationMap.remove(address) != null;
    }

}
