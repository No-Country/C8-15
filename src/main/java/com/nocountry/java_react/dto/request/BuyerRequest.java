package com.nocountry.java_react.dto.request;

import lombok.Data;

@Data
public class BuyerRequest {
    private String name;
    private String surname;
    private String document;
    private String email;
    private String userName;
    private String password;
    private String role;
}