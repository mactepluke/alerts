package com.safetynet.alerts.dao;

import com.safetynet.alerts.model.Firestation;
import java.util.List;

/**
 * Interface for DAO classes that handles objects of the Firestation type.
 */
public interface IFirestationDAO {
    /**
     * This method saves a new firestation in the repository or updates an existing one with the same address
     * @param firestation is the new firestation to store in the repository
     * @return the value of the old firestation station number entry in the repository
     * if matches the one of the firestation passed as parameter (null if non-existent)
     */
    String save(Firestation firestation);
    /**
     * This method deletes a firestation of a given address in the repository
     * @param address corresponds to the address/station pair to be deleted
     * @return the station value of the deleted address/station pair (null if non-existent)
     */
    String delete(String address);
    /**
     * This method deletes all firestations a given station value in the repository
     * @param station is the station value of all the address/station pairs to be deleted
     * @return TRUE if any address(es)/station(s) were deleted, FALSE otherwise
     */
    boolean deleteAllFirestationsOfNumber(String station);

    /**
     * This method gets the station value of a given address from the repository
     * @param address corresponds to the address/station pair to retrieve the station value from
     * @return the station value of the chosen address/station pair
     */
    String get(String address);

    /**
     * This method gets a list of all addresses of a given station value from the repository
     * @param station corresponds to the station value of all address/station pairs to retrieve the addresses from
     * @return a list of the addresses of all chosen address/station pairs
     */
    List<String> getAddresses(String station);

}
