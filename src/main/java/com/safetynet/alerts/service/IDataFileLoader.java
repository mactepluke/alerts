package com.safetynet.alerts.service;

import com.safetynet.alerts.repository.DataRepository;

/**
 * Interface for objects that load data from a file to an object of the DataRepository type
 */
public interface IDataFileLoader {
    /**
     * This method loads the content of the data from a file of a specified file path
     * @param dataFilePath the String value of the file path from where to read the file
     * @return a DataRepository-type object that contains the data
     */
    DataRepository loadDataFile(String dataFilePath);
}
