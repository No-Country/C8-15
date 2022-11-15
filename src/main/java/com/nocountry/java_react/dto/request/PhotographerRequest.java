package com.nocountry.java_react.dto.request;

import lombok.Data;

@Data
public class PhotographerRequest {
    
    private String name;
    private String surname;
    private String document;
    private String email;
    private String userName;
    private String password;
    private String role;
    private String telephone;
    private String city;
    private String country;
    private String facebookUrl;
    private String instagramUrl;
}