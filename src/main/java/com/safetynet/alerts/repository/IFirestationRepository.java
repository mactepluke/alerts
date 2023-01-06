package com.safetynet.alerts.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface IFirestationRepository {

    void putFirestation(String address, String firestationNumber);

    String getFirestationNumber(String address);

    boolean deleteFirestation(String address);

}
