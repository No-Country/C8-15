package com.nocountry.java_react.dto.request.buyer;

import lombok.Data;

@Data
public class BuyerRequestPassword {

    private String oldPassword;
    private String password;
    private String confirmPassword;
}
