package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.IFirestation;
import org.springframework.stereotype.Repository;

@Repository
public interface IFirestationRepository {

    void addFirestation(IFirestation firestation);

    IFirestation getFirestation(int id);

    boolean deleteFirestation(int id);

}
