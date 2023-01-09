package com.safetynet.alerts.dao;

import org.springframework.stereotype.Component;
import com.safetynet.alerts.model.Firestation;

import java.util.Map;

@Component
public interface IFirestationDAO {

    void saveFirestation(Firestation firestation);

    String deleteFirestation(String address);

    String getFirestationNumber(String address);

    Map<String, String> getFirestationsTable();

}
