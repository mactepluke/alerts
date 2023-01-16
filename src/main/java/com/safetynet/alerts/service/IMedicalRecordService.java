package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordService {
    MedicalRecord create(MedicalRecord newMedicalRecord);

    MedicalRecord update(String id, MedicalRecord medicalRecord);

    MedicalRecord delete(String id);
}
