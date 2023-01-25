package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DAO component that handles MedicalRecord-type objects in the repository
 */
@Component
public class MedicalRecordDAO implements IMedicalRecordDAO {

    @Autowired
    private DataRepository dataRepository;
    /**
     * @see IMedicalRecordDAO#save(MedicalRecord)
     */
    @Override
    public MedicalRecord save(MedicalRecord medicalRecord)    {
        return dataRepository.getMedicalRecordsTable().put(medicalRecord.getId(), medicalRecord);
    }
    /**
     * @see IMedicalRecordDAO#delete(String)
     */
    @Override
    public MedicalRecord delete(String id)    {
        return dataRepository.getMedicalRecordsTable().remove(id);
    }
    /**
     * @see IMedicalRecordDAO#get(String)
     */
    @Override
    public MedicalRecord get(String id)   {
        return dataRepository.getMedicalRecordsTable().get(id);
    }

}
