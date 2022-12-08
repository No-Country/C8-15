package com.nocountry.java_react.dto.request.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.java_react.validation.StartingWithBlankSpaces;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BuyerRequestModify {

    @NotBlank(message = "EL NOMBRE DEL COMPRADOR NO PUEDE ESTAR VACÍO O SER NULO")
    @Size(min = 3, max = 50, message = "EL NOMBRE DEL COMPRADOR DEBE TENER UN MÍNIMO DE 3 LETRAS Y UN MÁXIMO DE 50")
    @StartingWithBlankSpaces(message = "EL NOMBRE DEL COMPRADOR NO DEBE COMENZAR CON ESPACIOS EN BLANCO")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "EL APELLIDO DEL COMPRADOR NO PUEDE ESTAR VACÍO O SER NULO")
    @Size(min = 3, max = 50, message = "EL APELLIDO DEL COMPRADOR DEBE TENER UN MÍNIMO DE 3 LETRAS Y UN MÁXIMO DE 50")
    @StartingWithBlankSpaces(message = "EL APELLIDO DEL COMPRADOR NO DEBE COMENZAR CON ESPACIOS EN BLANCO")
    @JsonProperty("surname")
    private String surname;

    @NotBlank(message = "EL CORREO ELECTRÓNICO DEL COMPRADOR NO PUEDE ESTAR VACÍO O SER NULO")
    @Email(message = "EL CORREO ELECTRÓNICO DEL COMPRADOR DEBE TENER UN FORMATO CORRECTO")
    @StartingWithBlankSpaces(message = "EL CORREO ELECTRÓNICO DEL COMPRADOR NO DEBE COMENZAR CON ESPACIOS EN BLANCO")
    @JsonProperty("email")
    private String email;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country;
}