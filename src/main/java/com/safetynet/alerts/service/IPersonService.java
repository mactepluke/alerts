package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;

/**
 * Interface for services handling model objects of Person type
 */
public interface IPersonService {
    /**
     * This method creates a new person in the repository
     * @param newPerson the value of the new Person object to be created
     * @return null if the new person is created, or the value of the Person object that already exists
     */
    Person create(Person newPerson);
    /**
     * This method updates a person in the repository with new data
     * @param id the id of the person whose data must be updated
     * @param person the new person data that must replace the current one
     * @return the new value of the Person object that has been updated, or null if its id does not exist
     */
    Person update(String id, Person person);
    /**
     * This method deletes person in the repository
     * @param id the id of the person that must be deleted
     * @return the value of the deleted Person object, or null if its id does not exist
     */
    Person delete(String id);
}
