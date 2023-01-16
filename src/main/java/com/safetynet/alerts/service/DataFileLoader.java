package com.safetynet.alerts.service;

import com.jsoniter.JsonIterator;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataFileLoader implements IDataFileLoader {

    private static final Logger logger = LogManager.getLogger(DataFileLoader.class);

    IDataLists dataLists = new DataLists();

    @Override
    public DataRepository loadDataFile(String dataFilePath) {

        DataRepository dataRepository;

        createDataListsFromString(loadDataFileToString(dataFilePath));
        dataRepository = saveDataListToRepository();

        return dataRepository;
    }

    private String loadDataFileToString(String dataFilePath) {

        StringBuilder loadedFile = new StringBuilder();
        String line = "";

        loadedFile.append(line);

        try (BufferedReader reader = new BufferedReader(new FileReader(dataFilePath))) {

            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    loadedFile.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedFile.toString();
    }

    private DataRepository saveDataListToRepository() {

        DataRepository dataRepository = new DataRepository();

        List<Person> persons = dataLists.getPersonsList();
        List<Firestation> firestations = dataLists.getFirestationsList();
        List<MedicalRecord> medicalRecords = dataLists.getMedicalrecordsList();

        Map<String, Person> personsTable = new HashMap<>();
        Map<String, MedicalRecord> medicalRecordsTable = new HashMap<>();
        Map<String, String> firestationsTable = new HashMap<>();

        for (Person person : persons) {
            personsTable.put(person.getId(), person);
        }

        for (MedicalRecord medicalRecord : medicalRecords) {
            medicalRecordsTable.put(medicalRecord.getId(), medicalRecord);
        }

        for (Firestation firestation : firestations) {
            firestationsTable.put(firestation.getAddress(), firestation.getStationNumber());
        }

        dataRepository.setPersonsTable(personsTable);
        dataRepository.setMedicalRecordsTable(medicalRecordsTable);
        dataRepository.setFirestationsTable(firestationsTable);

        return dataRepository;
    }

    private void createDataListsFromString(String input) {

        dataLists = JsonIterator.deserialize(input, DataLists.class);

        }

}
