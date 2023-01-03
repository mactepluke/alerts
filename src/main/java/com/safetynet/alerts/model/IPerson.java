package com.safetynet.alerts.model;

public interface IPerson {

    int getId();

    String getFirstName();

    String getLastName();

    void setAddress(String address);

    String getAddress();

    void setCity(String city);

    String getCity();

    void setZip(String zip);

    String getZip();

    void setPhone(String phone);

    String getPhone();

    void setEmail(String email);

    String getEmail();

}
