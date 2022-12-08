package com.nocountry.java_react.dto.response;

import lombok.Data;

@Data
public class PhotoResponse {

    private String idPhoto;
    private String originalName;
    private String fileName;
    private String path;
    private String category;
    private String author;
    private String location;
    private String description;
    private Double price;
    private String paymentLink;
    private String created;
    private String updated;
    private String deleted;
}
