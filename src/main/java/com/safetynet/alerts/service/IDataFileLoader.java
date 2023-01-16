package com.safetynet.alerts.service;

import com.safetynet.alerts.repository.DataRepository;

public interface IDataFileLoader {
    DataRepository loadDataFile(String dataFilePath);
}
