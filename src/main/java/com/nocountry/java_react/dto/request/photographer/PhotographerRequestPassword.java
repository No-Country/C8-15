package com.nocountry.java_react.dto.request.photographer;

import lombok.Data;

@Data
public class PhotographerRequestPassword {

    private String oldPassword;
    private String password;
    private String confirmPassword;
}
