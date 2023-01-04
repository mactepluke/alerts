package com.safetynet.alerts.service;

import com.safetynet.alerts.model.IPerson;
import org.springframework.stereotype.Service;

@Service
public interface IPersonService {

    IPerson savePerson(IPerson person);

}
