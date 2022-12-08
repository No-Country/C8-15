package com.nocountry.java_react.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PhotoRequest {

    @NotBlank(message = "LA CATEGORÍA DE LA FOTO NO PUEDE ESTAR VACÍA O SER NULA")
    @JsonProperty("category")
    private String category;

    @NotBlank(message = "EL AUTOR DE LA FOTO NO PUEDE ESTAR VACÍO O SER NULO")
    @JsonProperty("author")
    private String author;

    @JsonProperty("location")
    private String location;

    @JsonProperty("description")
    private String description;

    @NotBlank(message = "EL PRECIO DE LA FOTO NO PUEDE ESTAR VACÍO O SER NULO")
    @JsonProperty("price")
    private String price;

    @NotBlank(message = "EL LINK DE PAGO DE LA FOTO NO PUEDE ESTAR VACÍO O SER NULO")
    @JsonProperty("paymentLink")
    private String paymentLink;
}