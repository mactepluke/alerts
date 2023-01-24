package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * DAO component that handles Firestation-type objects in the repository
 */
@Component
public class FirestationDAO implements IFirestationDAO  {

        @Autowired
        private DataRepository dataRepository;
        /**
         * @see IFirestationDAO#save(Firestation)
         */
        @Override
        public String save(Firestation firestation)    {
                return dataRepository.getFirestationsTable().put(firestation.getAddress(), firestation.getStationNumber());
        }
        /**
         * @see IFirestationDAO#delete(String)
         */
        @Override
        public String delete(String address)    {
                return dataRepository.getFirestationsTable().remove(address);
        }
        /**
         * @see IFirestationDAO#deleteAllFirestationsOfNumber(String)
         */
        @Override
        public boolean deleteAllFirestationsOfNumber(String station)    {
                return dataRepository.getFirestationsTable().values().removeAll(Collections.singleton(station));

        }
        /**
         * @see IFirestationDAO#get(String)
         */
        @Override
        public String get(String address)   {
                return dataRepository.getFirestationsTable().get(address);
        }
        /**
         * @see IFirestationDAO#getAddresses(String)
         */
        @Override
        public List<String> getAddresses(String station)   {
                int addressCount = 0;
                List<String> addresses = new ArrayList<>();
                Map<String, String> firestationsTable = dataRepository.getFirestationsTable();

                for (Map.Entry<String, String> entry : firestationsTable.entrySet()) {

                        if (Objects.equals(entry.getValue(), station))        {
                                addresses.add(entry.getKey());
                                addressCount++;
                        }
                }
                if (addressCount == 0)  {
                        addresses = null;
                }

                return addresses;
        }

}
