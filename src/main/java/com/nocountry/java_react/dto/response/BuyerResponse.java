package com.nocountry.java_react.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BuyerResponse {

    private String idPhotographer;
    private String name;
    private String surname;
    private String document;
    private String email;
    private String userName;
    private String role;
    private String telephone;
    private String city;
    private String country;
    private List<PhotoResponse> photos;
    private String created;
    private String updated;
    private String deleted;
}