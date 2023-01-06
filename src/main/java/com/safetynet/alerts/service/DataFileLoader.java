package com.safetynet.alerts.service;

import com.jsoniter.JsonIterator;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class DataFileLoader {

    private static final Logger logger = LogManager.getLogger(DataFileLoader.class);

    private static final String DATA_FILE_PATH = "src/main/resources/data.json";

    @Autowired
    static DataLists dataLists;


    public void loadDataFile()  {
        saveDataListToRepository(createDataListsFromString(loadDataFileToString()));

    }

    private static String loadDataFileToString()   {

        StringBuilder loadedFile = new StringBuilder();
        String line = "";

        loadedFile.append(line);

        try (BufferedReader reader = new BufferedReader(new FileReader(DataFileLoader.DATA_FILE_PATH))) {

            while (line != null)    {
                line = reader.readLine();
                if (line != null) {
                    loadedFile.append(line);
                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return loadedFile.toString();
    }

    private static void saveDataListToRepository(DataLists dataLists)   {
        //TODO: implement this method
    }

    public static DataLists createDataListsFromString(String input)   {

        List<Person> persons;
        List<Firestation> firestations;
        List<MedicalRecord> medicalRecords;

        dataLists = JsonIterator.deserialize(input, DataLists.class);

        persons = dataLists.getPersonsList();
        firestations = dataLists.getFirestationsList();
        medicalRecords = dataLists.getMedicalrecordsList();


        logger.debug("=====================   PERSONS   ====================");

        if (persons != null) {
            for (Person person : persons) {
                logger.debug(person.getId());
                logger.debug(person.getFirstName());
                logger.debug(person.getLastName());
                logger.debug(person.getAddress());
                logger.debug(person.getCity());
                logger.debug(person.getZip());
                logger.debug(person.getPhone());
                logger.debug(person.getEmail());
                logger.debug("-----------------------");
            }
        }

        logger.debug("===================== FIRESTATIONS ====================");

        if (firestations != null) {
            for (Firestation firestation : firestations) {
                logger.debug(firestation.getAddress());
                logger.debug(firestation.getStationNumber());
                logger.debug("-----------------------");
            }
        }

        logger.debug("===================== MED  RECORDS ====================");

        if (medicalRecords != null) {
            for (MedicalRecord medicalRecord : medicalRecords) {
                logger.debug(medicalRecord.getId());
                logger.debug(medicalRecord.getFirstName());
                logger.debug(medicalRecord.getLastName());
                logger.debug(medicalRecord.getBirthdate());
                logger.debug(medicalRecord.getMedications());
                logger.debug(medicalRecord.getAllergies());
                logger.debug("-----------------------");
            }
        }

        return dataLists;
    }

}
