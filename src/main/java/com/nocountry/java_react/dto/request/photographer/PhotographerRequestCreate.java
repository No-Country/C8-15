package com.nocountry.java_react.dto.request.photographer;

import lombok.Data;

@Data
public class PhotographerRequestCreate {
    
    private String name;
    private String surname;
    private String email;
    private String password;
    private String confirmPassword;
}