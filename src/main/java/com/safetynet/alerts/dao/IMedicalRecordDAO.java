package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordDAO {

    MedicalRecord save(MedicalRecord medicalRecord);

    MedicalRecord delete(String id);

    MedicalRecord get(String id);

}
