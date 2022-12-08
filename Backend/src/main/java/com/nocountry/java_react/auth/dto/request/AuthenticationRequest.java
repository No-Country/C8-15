package com.nocountry.java_react.auth.dto.request;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;
    private String password;
}