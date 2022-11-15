package com.nocountry.java_react.dto.response;

import lombok.Data;

@Data
public class BuyerResponse {

    private String idPhotographer;
    private String name;
    private String surname;
    private String document;
    private String email;
    private String userName;
    private String role;
    private String registrationDate;
    private String modificationDate;
    private String softDelete;
}