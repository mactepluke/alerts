package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;

/**
 * Interface for services handling model objects of Firestation type
 */
public interface IFirestationService {
    /**
     * This method creates a new firestation in the repository
     * @param newFirestation the value of the new Firestation object to be created
     * @return null if the new firestation is created, or the value of the station that already exists
     */
    String create(Firestation newFirestation);
    /**
     * This method updates a firestation in the repository with new data
     * @param address the address of the firestation whose station value must be updated
     * @param station the new station value that must replace the current one
     * @return the new value of the station that has been updated, or null if its id does not exist
     */
    Firestation update(String address, String station);
    /**
     * This method deletes firestation in the repository
     * @param value the address of the firestation that must be deleted
     *              or the station value of all address/station pairs that must be deleted
     * @return the value of the deleted Firestation object, or null if its id does not exist.
     *              In the event of multiple address/station pairs deleted for one station,
     *              the value of the Firestation object returned may contain "ALL" as its "address" field
     */
    Firestation delete(String value);
}
