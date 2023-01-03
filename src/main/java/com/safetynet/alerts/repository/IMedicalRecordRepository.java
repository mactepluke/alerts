package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.IMedicalRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalRecordRepository {

    void addMedicalRecord(IMedicalRecord medicalRecord);

    IMedicalRecord getMedicalRecord(int id);

    boolean deleteMedicalRecord(int id);
}
