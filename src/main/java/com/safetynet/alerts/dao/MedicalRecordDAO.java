package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.IMedicalRecord;
import com.safetynet.alerts.repository.IMedicalRecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicalRecordDAO implements IMedicalRecordDAO {

    private static final Logger logger = LogManager.getLogger(MedicalRecordDAO.class);

    @Autowired
    private IMedicalRecordRepository medicalRecordRepository;

    @Override
    public void saveMedicalRecord(IMedicalRecord medicalRecord)    {
        medicalRecordRepository.addMedicalRecord(medicalRecord);
    }

    @Override
    public boolean deleteMedicalRecord(int id)    {
        return medicalRecordRepository.deleteMedicalRecord(id);
    }

    @Override
    public IMedicalRecord getMedicalRecord(int id)   {
        return medicalRecordRepository.getMedicalRecord(id);
    }
}
