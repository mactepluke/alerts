package com.safetynet.alerts.service;

import com.safetynet.alerts.dao.IFirestationDAO;
import com.safetynet.alerts.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FirestationService implements IFirestationService {

    @Autowired
    private IFirestationDAO firestationDAO;

    @Override
    public Firestation create(Firestation newFirestation) {

        return firestationDAO.save(newFirestation);
    }

    @Override
    public Firestation update(String address, String station) {

        Optional<String> e = Optional.ofNullable(firestationDAO.get(address));

        if (e.isPresent() && address != null) {

            Firestation currentFirestation = new Firestation(address, station);
            firestationDAO.save(currentFirestation);

            return currentFirestation;

        } else {
            return null;
        }
    }

    @Override
    public Firestation delete(String value) {

        String result = firestationDAO.delete(value);
        Firestation firestation = null;

        if (result != null) {
            firestation = new Firestation(value, result);
        } else {
            if (firestationDAO.deleteAllFirestationsOfNumber(value)) {
                firestation = new Firestation("ALL", value);
            }
        }

        return firestation;
    }

}
