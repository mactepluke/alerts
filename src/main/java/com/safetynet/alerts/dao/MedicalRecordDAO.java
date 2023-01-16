package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicalRecordDAO implements IMedicalRecordDAO {

    private static final Logger logger = LogManager.getLogger(MedicalRecordDAO.class);

    @Autowired
    private DataRepository dataRepository;

    @Override
    public void save(MedicalRecord medicalRecord)    {
        dataRepository.getMedicalRecordsTable().put(medicalRecord.getId(), medicalRecord);
    }

    @Override
    public MedicalRecord delete(String id)    {
        return dataRepository.getMedicalRecordsTable().remove(id);
    }

    @Override
    public MedicalRecord get(String id)   {
        return dataRepository.getMedicalRecordsTable().get(id);
    }

}
