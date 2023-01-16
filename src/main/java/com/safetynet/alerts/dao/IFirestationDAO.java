package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Firestation;
import java.util.List;

public interface IFirestationDAO {

    Firestation save(Firestation firestation);

    String delete(String address);

    boolean deleteAllFirestationsOfNumber(String station);

    String get(String address);

    List<String> getAddresses(String station);

}
