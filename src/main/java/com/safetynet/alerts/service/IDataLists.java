package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import java.util.List;

/**
 * Interface for objects that store lists of the Person, MedicalRecord and Firestation types
 */
public interface IDataLists {
    /**
     * This method returns a list of persons
     * @return a list of objects of the Person type
     */
    List<Person> getPersonsList();
    /**
     * This method returns a list of firestations
     * @return a list of objects of the Firestation type
     */
    List<Firestation> getFirestationsList();
    /**
     * This method returns a list of medical records
     * @return a list of objects of the MedicalRecord type
     */
    List<MedicalRecord> getMedicalrecordsList();
}
