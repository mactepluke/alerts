package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;

/**
 * Interface for services handling model objects of MedicalRecord type
 */
public interface IMedicalRecordService {
    /**
     * This method creates a new medical record in the repository
     * @param newMedicalRecord the value of the new MedicalRecord object to be created
     * @return null if the new medical record is created, or the value of the MedicalRecord object that already exists
     */
    MedicalRecord create(MedicalRecord newMedicalRecord);
    /**
     * This method updates a medical record in the repository with new data
     * @param id the id of the medical record whose data must be updated
     * @param medicalRecord the new medical record data that must replace the current one
     * @return the new value of the MedicalRecord object that has been updated, or null if its id does not exist
     */
    MedicalRecord update(String id, MedicalRecord medicalRecord);
    /**
     * This method deletes medical record in the repository
     * @param id the id of the medical record that must be deleted
     * @return the value of the deleted MedicalRecord object, or null if its id does not exist
     */
    MedicalRecord delete(String id);
}
