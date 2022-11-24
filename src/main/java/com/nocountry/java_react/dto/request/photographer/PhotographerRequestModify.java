package com.nocountry.java_react.dto.request.photographer;

import lombok.Data;

@Data
public class PhotographerRequestModify {
    
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private String city;
    private String country;
    private String facebookUrl;
    private String instagramUrl;
}