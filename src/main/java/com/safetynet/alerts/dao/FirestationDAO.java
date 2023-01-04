package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.IFirestation;
import com.safetynet.alerts.repository.IFirestationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirestationDAO implements IFirestationDAO  {

        private static final Logger logger = LoggerFactory.getLogger(FirestationDAO.class);

        @Autowired
        private IFirestationRepository firestationRepository;

        @Override
        public void saveFirestation(IFirestation firestation)    {
                firestationRepository.putFirestation(firestation.getAddress(), firestation.getNumber());
        }

        @Override
        public boolean deleteFirestation(String address)    {
                return firestationRepository.deleteFirestation(address);
        }

        @Override
        public IFirestation getFirestation(String address)   {
                return new Firestation(address, firestationRepository.getFirestationNumber(address));
        }

}
