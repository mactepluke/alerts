package com.safetynet.alerts.dao;

import org.springframework.stereotype.Component;
import com.safetynet.alerts.model.MedicalRecord;


@Component
public interface IMedicalRecordDAO {

    void save(MedicalRecord medicalRecord);

    MedicalRecord delete(String id);

    MedicalRecord get(String id);

}
