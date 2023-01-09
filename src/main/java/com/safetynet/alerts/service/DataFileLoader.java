package com.safetynet.alerts.service;

import com.jsoniter.JsonIterator;
import com.safetynet.alerts.dao.IFirestationDAO;
import com.safetynet.alerts.dao.IMedicalRecordDAO;
import com.safetynet.alerts.dao.IPersonDAO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

//@Bean
public class DataFileLoader implements IDataFileLoader  {

    private static final Logger logger = LogManager.getLogger(DataFileLoader.class);

    private final String dataFilePath;

    @Autowired
    private IDataLists dataLists;

    @Autowired
    private IPersonDAO personDAO;

    @Autowired
    private IMedicalRecordDAO medicalRecordDAO;

    @Autowired
    private IFirestationDAO firestationDAO;

    public DataFileLoader(String dataFilePath)   {
        this.dataFilePath = dataFilePath;
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
            personDAO.savePerson(person);
        }

        for (MedicalRecord medicalRecord : medicalRecords) {
            medicalRecordDAO.saveMedicalRecord(medicalRecord);
        }

        for (Firestation firestation : firestations) {
            firestationDAO.saveFirestation(firestation);
        }

    }

    private void createDataListsFromString(String input) {

        dataLists = JsonIterator.deserialize(input, DataLists.class);

        }

}
