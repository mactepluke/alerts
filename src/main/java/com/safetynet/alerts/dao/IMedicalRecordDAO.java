package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Component;
import com.safetynet.alerts.model.MedicalRecord;

import java.util.Map;


@Component
public interface IMedicalRecordDAO {

    void saveMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord deleteMedicalRecord(String id);

    MedicalRecord getMedicalRecord(String id);

    Map<String, MedicalRecord> getMedicalRecordsTable();

}
