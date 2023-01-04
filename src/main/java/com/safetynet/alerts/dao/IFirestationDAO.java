package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.IFirestation;
import org.springframework.stereotype.Component;

@Component
public interface IFirestationDAO {

    void saveFirestation(IFirestation firestation);

    boolean deleteFirestation(String address);

    IFirestation getFirestation(String address);

}
