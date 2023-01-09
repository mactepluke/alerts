package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MedicalRecordDAO implements IMedicalRecordDAO {

    private static final Logger logger = LogManager.getLogger(MedicalRecordDAO.class);
    private final Map<String, MedicalRecord> medicalRecordsRepository = new HashMap<>();

    @Override
    public void saveMedicalRecord(MedicalRecord medicalRecord)    {
        medicalRecordsRepository.put(medicalRecord.getId(), medicalRecord);
    }

    @Override
    public MedicalRecord deleteMedicalRecord(String id)    {
        return medicalRecordsRepository.remove(id);
    }

    @Override
    public MedicalRecord getMedicalRecord(String id)   {
        return medicalRecordsRepository.get(id);
    }

    @Override
    public Map<String, MedicalRecord> getMedicalRecordsTable()  {
        return medicalRecordsRepository;
    }
}
