package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Person;

import java.util.List;

/**
 * Interface for DAO classes that handles objects of the Person type.
 */
public interface IPersonDAO {
    enum FieldType   {
        ADDRESS, CITY, LAST_NAME
    }
    /**
     * This method saves a new person in the repository or updates an existing one with the same id
     * @param person is the new person to store in the repository
     * @return the value of the old person entry in the repository
     * if matches the one of the person passed as parameter (null if non-existent)
     */
    Person save(Person person);
    /**
     * This method deletes a person of a given id in the repository
     * @param id corresponds to the id of the person to be deleted
     * @return the value of the deleted person (null if non-existent)
     */
    Person delete(String id);
    /**
     * This method gets the person of a given id from the repository
     * @param id corresponds to id of the person value to be retrieved
     * @return the person value of the chosen id
     */
    Person get(String id);
    /**
     * This method gets a list of persons sharing a given field value from the repository
     * @param fieldType one of the 3 types of FieldType values to look for from the following: {ADDRESS, CITY, LAST_NAME}
     * @param fieldValue the value of the field to look for
     * @return the list of persons sharing the same field value
     */
    List<Person> getPersonsListByField(FieldType fieldType, String fieldValue);
}
