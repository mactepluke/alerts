package com.safetynet.alerts.service;

import com.safetynet.alerts.dao.IMedicalRecordDAO;
import com.safetynet.alerts.model.MedicalRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * A service handling model objects of MedicalRecord type
 */
@Service
public class MedicalRecordService implements IMedicalRecordService  {

    @Autowired
    private IMedicalRecordDAO medicalRecordDAO;

    /**
     * @see IMedicalRecordService#create(MedicalRecord)
     */
    @Override
    public MedicalRecord create(MedicalRecord newMedicalRecord) {

        MedicalRecord result = medicalRecordDAO.get(newMedicalRecord.getId());

        if (result == null) {
            result = medicalRecordDAO.save(newMedicalRecord);
        }
        return result;

    }
    /**
     * @see IMedicalRecordService#update(String, MedicalRecord)
     */
    @Override
    public MedicalRecord update(String id, MedicalRecord medicalRecord) {

        Optional<MedicalRecord> e = Optional.ofNullable(medicalRecordDAO.get(id));

            if (e.isPresent()) {

                MedicalRecord currentMedicalRecord = e.get();

                String birthdate = medicalRecord.getBirthdate();
                if (birthdate != null) {
                    currentMedicalRecord.setBirthdate(birthdate);
                }

                List<String> medications = medicalRecord.getMedications();
                if (medications != null) {
                    currentMedicalRecord.setMedications(medications);
                }

                List<String> allergies = medicalRecord.getAllergies();
                if (allergies != null) {
                    currentMedicalRecord.setAllergies(allergies);
                }

            medicalRecordDAO.save(currentMedicalRecord);

            return currentMedicalRecord;

        } else {
            return null;
        }
    }
    /**
     * @see IMedicalRecordService#delete(String)
     */
    @Override
    public MedicalRecord delete(String id) {

        return medicalRecordDAO.delete(id);
    }

}
