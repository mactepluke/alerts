package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.IFirestation;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FirestationRepository implements IFirestationRepository    {

    final Map<Integer, IFirestation> firestationMap;

    public FirestationRepository() {
        this.firestationMap = new HashMap<>();
    }

    @Override
    public void addFirestation(IFirestation firestation)    {
        this.firestationMap.put(firestation.getStationNumber(), firestation);
    }

    @Override
    public IFirestation getFirestation(int id)    {
        return this.firestationMap.get(id);
    }

    @Override
    public boolean deleteFirestation(int id)    {
        return this.firestationMap.remove(id) != null;
    }

}
