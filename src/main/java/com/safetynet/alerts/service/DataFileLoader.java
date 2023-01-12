package com.safetynet.alerts.service;

import com.jsoniter.JsonIterator;
import com.safetynet.alerts.dao.*;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DataFileLoader {

    private static final Logger logger = LogManager.getLogger(DataFileLoader.class);

    private final IPersonDAO personDAO;
    private final IMedicalRecordDAO medicalRecordDAO;
    private final IFirestationDAO firestationDAO;
    private IDataLists dataLists;

    public DataFileLoader(String dataFilePath, IPersonDAO personDAO, IMedicalRecordDAO medicalRecordDAO, IFirestationDAO firestationDAO, IDataLists dataLists)   {
        this.personDAO = personDAO;
        this.medicalRecordDAO = medicalRecordDAO;
        this.firestationDAO = firestationDAO;
        this.dataLists = dataLists;
        loadDataFile(dataFilePath);
    }

    public void loadDataFile(String dataFilePath) {

        createDataListsFromString(loadDataFileToString(dataFilePath));
        saveDataListToRepository();
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

    private void saveDataListToRepository() {

        List<Person> persons = dataLists.getPersonsList();
        List<Firestation> firestations = dataLists.getFirestationsList();
        List<MedicalRecord> medicalRecords = dataLists.getMedicalrecordsList();

        for (Person person : persons) {
            personDAO.save(person);
        }

        for (MedicalRecord medicalRecord : medicalRecords) {
            medicalRecordDAO.save(medicalRecord);
        }

        for (Firestation firestation : firestations) {
            firestationDAO.save(firestation);
        }

    }

    private void createDataListsFromString(String input) {

        dataLists = JsonIterator.deserialize(input, DataLists.class);

        }

}
