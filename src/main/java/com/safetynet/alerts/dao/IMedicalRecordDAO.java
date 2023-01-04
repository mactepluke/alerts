package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.IMedicalRecord;
import org.springframework.stereotype.Component;


@Component
public interface IMedicalRecordDAO {

    void saveMedicalRecord(IMedicalRecord medicalRecord);

    boolean deleteMedicalRecord(int id);

    IMedicalRecord getMedicalRecord(int id);

}
