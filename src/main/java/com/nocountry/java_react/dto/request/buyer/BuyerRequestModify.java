package com.nocountry.java_react.dto.request.buyer;

import lombok.Data;

@Data
public class BuyerRequestModify {
    
    private String name;
    private String surname;
    private String email;
    private String userName;
    private String telephone;
    private String city;
    private String country;
}