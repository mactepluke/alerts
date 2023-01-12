package com.safetynet.alerts.dao;

import org.springframework.stereotype.Component;
import com.safetynet.alerts.model.Firestation;

@Component
public interface IFirestationDAO {

    void save(Firestation firestation);

    String delete(String address);

    boolean deleteAllFirestationsOfNumber(String station);

    String get(String address);

}
