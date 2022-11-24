package com.nocountry.java_react.dto.request.buyer;

import lombok.Data;

@Data
public class BuyerRequestCreate {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String confirmPassword;
}