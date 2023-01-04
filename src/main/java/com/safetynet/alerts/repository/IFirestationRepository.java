package com.safetynet.alerts.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface IFirestationRepository {

    void putFirestation(String address, int firestationNumber);

    int getFirestationNumber(String address);

    boolean deleteFirestation(String address);

}
