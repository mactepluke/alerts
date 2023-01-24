package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.MedicalRecord;

/**
 * Interface for DAO classes that handles objects of the MedicalRecord type.
 */
public interface IMedicalRecordDAO {
    /**
     * This method saves a new medical record in the repository or updates an existing one with the same id
     * @param medicalRecord is the new medical record to store in the repository
     * @return the value of the old medical record entry in the repository
     * if matches the one of the medical record passed as parameter (null if non-existent)
     */
    MedicalRecord save(MedicalRecord medicalRecord);
    /**
     * This method gets the medical record of a given id from the repository
     * @param id corresponds to id of the medical record value to be retrieved
     * @return the medical record value of the chosen id
     */
    MedicalRecord delete(String id);
    /**
     * This method gets the medical record of a given id from the repository
     * @param id corresponds to id of the medical record value to be retrieved
     * @return the medical record value of the chosen id
     */
    MedicalRecord get(String id);

}
