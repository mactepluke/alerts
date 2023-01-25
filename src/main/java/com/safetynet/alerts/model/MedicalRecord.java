package com.safetynet.alerts.model;

import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;

/**
 * Basic model type for medical record objects
 */
public class MedicalRecord {
    private static final byte ADULT_AGE = 19;
    private final String id;
    private final String firstName;
    private final String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    private static final Logger logger = LogManager.getLogger(MedicalRecord.class);

    @JsonCreator
    public MedicalRecord(@JsonProperty(value = "firstName", required = true) String firstName, @JsonProperty(value = "lastName", required = true) String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = firstName + lastName;
        this.medications = new ArrayList<>();
        this.allergies = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthdate() {
        return this.birthdate;
    }

    public byte getAge() {
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        byte result = -1;

        if (this.birthdate != null) {

            try {
                LocalDate birthDate = LocalDate.parse(this.birthdate, datePattern);
                LocalDate today = LocalDate.now();
                result = (byte) YEARS.between(birthDate, today);

            } catch (Exception e) {
                result = -2;
                logger.error("Incorrect birthdate format: {}", this.birthdate);
            }
        }

        return result;
    }

    public boolean isAChild() {

        return (this.getAge() < ADULT_AGE);
    }

    public List<String> getMedications() {
        return this.medications;
    }

    public List<String> getAllergies() {
        return this.allergies;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return "firstName: " + this.firstName
                + ", lastName: " + this.lastName
                + ", birthdate: " + this.birthdate
                + ", medications: " + this.medications
                + ", allergies: " + this.allergies;
    }


}
