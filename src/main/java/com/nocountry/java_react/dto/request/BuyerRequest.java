package com.nocountry.java_react.dto.request;

import lombok.Data;

@Data
public class BuyerRequest {

    private String name;
    private String surname;
    private String email;
    private String userName;
    private String password;
    private String telephone;
    private String city;
    private String country;
}