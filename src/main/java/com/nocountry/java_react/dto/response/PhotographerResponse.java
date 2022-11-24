package com.nocountry.java_react.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PhotographerResponse {

    private String idPhotographer;
    private String name;
    private String surname;
    private String email;
    private String role;
    private String telephone;
    private String city;
    private String country;
    private String facebookUrl;
    private String instagramUrl;
    private List<PhotoResponse> photos;
    private String created;
    private String updated;
    private String deleted;
}