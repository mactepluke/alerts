package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Firestation;

import com.safetynet.alerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FirestationDAO implements IFirestationDAO  {

        private static final Logger logger = LogManager.getLogger(FirestationDAO.class);
        private final Map<String, String> firestationsRepository = new HashMap<>();

        @Override
        public void saveFirestation(Firestation firestation)    {
                firestationsRepository.put(firestation.getAddress(), firestation.getStationNumber());
        }

        @Override
        public String deleteFirestation(String address)    {
                return firestationsRepository.remove(address);
        }

        @Override
        public String getFirestationNumber(String address)   {
                return firestationsRepository.get(address);
        }

        @Override
        public Map<String, String> getFirestationsTable()      {
                return firestationsRepository;
        }

}
