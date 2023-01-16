package com.safetynet.alerts.service;

import com.safetynet.alerts.dao.IMedicalRecordDAO;
import com.safetynet.alerts.model.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService implements IMedicalRecordService  {

    @Autowired
    private IMedicalRecordDAO medicalRecordDAO;

    private static final Logger logger = LogManager.getLogger(PersonService.class);

    @Override
    public MedicalRecord create(MedicalRecord newMedicalRecord) {

        return medicalRecordDAO.save(newMedicalRecord);

    }

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

                    for (String medication : medications) {
                        currentMedicalRecord.addMedication(medication);
                    }
                }

                List<String> allergies = medicalRecord.getAllergies();
                if (allergies != null) {

                    for (String allergy : allergies) {
                        currentMedicalRecord.addAllergy(allergy);
                    }
                }

            medicalRecordDAO.save(currentMedicalRecord);

            return currentMedicalRecord;

        } else {
            return null;
        }
    }

    @Override
    public MedicalRecord delete(String id) {

        return medicalRecordDAO.delete(id);
    }

}
