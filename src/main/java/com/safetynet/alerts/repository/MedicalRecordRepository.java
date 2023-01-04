package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.IMedicalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository  {

    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordRepository.class);

    final Map<Integer, IMedicalRecord> medicalRecordMap;

    public MedicalRecordRepository() {
        this.medicalRecordMap = new HashMap<>();
    }

    @Override
    public void addMedicalRecord(IMedicalRecord medicalRecord) {
        this.medicalRecordMap.put(medicalRecord.getId(), medicalRecord);
    }

    @Override
    public IMedicalRecord getMedicalRecord(int id)    {
        return this.medicalRecordMap.get(id);
    }

    @Override
    public boolean deleteMedicalRecord(int id) {
        return this.medicalRecordMap.remove(id) != null;
    }
}
