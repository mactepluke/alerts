package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Firestation;

import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.lineSeparator;

@Component
public class FirestationDAO implements IFirestationDAO  {

        private static final Logger logger = LogManager.getLogger(FirestationDAO.class);

        @Autowired
        private DataRepository dataRepository;

        @Override
        public void save(Firestation firestation)    {
                dataRepository.getFirestationsTable().put(firestation.getAddress(), firestation.getStationNumber());
        }

        @Override
        public String delete(String address)    {
                return dataRepository.getFirestationsTable().remove(address);
        }

        @Override
        public boolean deleteAllFirestationsOfNumber(String station)    {

                return dataRepository.getFirestationsTable().values().removeAll(Collections.singleton(station));

        }

        @Override
        public String get(String address)   {
                return dataRepository.getFirestationsTable().get(address);
        }

}
