package com.nocountry.java_react.dto.request;

import lombok.Data;

@Data
public class PhotoRequest {

    private String originalName;
    private String fileName;
    private String path;
    private String category;
    private String author;
    private String location;
}