package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdvancedRequestController {

    @GetMapping("/firestation?stationNumber=<{stationNumber}>")
    public List<Person> getPersons(@PathVariable("stationNumber") final String station) {
       /* Optional<String> address = firestationDAO.get(station);

        if(firestation.isPresent()) {
            return firestation.get();
        } else {*/
        return null;
        //}
    }
}
