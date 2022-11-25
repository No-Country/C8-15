package com.nocountry.java_react.dto.request.photographer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.java_react.validation.Password;
import com.nocountry.java_react.validation.StartingWithBlankSpaces;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PhotographerRequestCreate {

    @NotBlank(message = "EL NOMBRE DEL FOTÓGRAFO NO PUEDE ESTAR VACÍO O SER NULO")
    @Size(min = 3, max = 50, message = "EL NOMBRE DEL FOTÓGRAFO DEBE TENER UN MÍNIMO DE 3 LETRAS Y UN MÁXIMO DE 50")
    @StartingWithBlankSpaces(message = "EL NOMBRE DEL FOTÓGRAFO NO DEBE COMENZAR CON ESPACIOS EN BLANCO")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "EL NOMBRE DEL FOTÓGRAFO NO PUEDE ESTAR VACÍO O SER NULO")
    @Size(min = 3, max = 50, message = "EL NOMBRE DEL FOTÓGRAFO DEBE TENER UN MÍNIMO DE 3 LETRAS Y UN MÁXIMO DE 50")
    @StartingWithBlankSpaces(message = "EL NOMBRE DEL FOTÓGRAFO NO DEBE COMENZAR CON ESPACIOS EN BLANCO")
    @JsonProperty("surname")
    private String surname;

    @NotBlank(message = "EL CORREO ELECTRÓNICO DEL FOTÓGRAFO NO PUEDE ESTAR VACÍO O SER NULO")
    @Email(message = "EL CORREO ELECTRÓNICO DEL FOTÓGRAFO DEBE TENER UN FORMATO CORRECTO")
    @StartingWithBlankSpaces(message = "EL CORREO ELECTRÓNICO DEL FOTÓGRAFO NO DEBE COMENZAR CON ESPACIOS EN BLANCO")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "EL PASSWORD DEL FOTÓGRAFO NO PUEDE ESTAR VACÍO O SER NULO")
    @Size(min = 6, max = 16, message = "EL PASSWORD DEL FOTÓGRAFO DEBE TENER UN MÍNIMO DE 6 CARACTERES Y UN MÁXIMO DE 16")
    @Password(message = "EL PASSWORD DEL FOTÓGRAFO DEBE TENER AL MENOS 1 CARÁCTER EN MAYÚSCULA, 1 CARÁCTER EN MINÚSCULA, 1 NÚMERO Y 1 CARÁCTER ESPECIAL (EJEMPLO: 1Password$)")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "LA CONFIRMACIÓN DEL PASSWORD DEL FOTÓGRAFO NO PUEDE ESTAR VACÍO O SER NULO")
    @Size(min = 6, max = 16, message = "LA CONFIRMACIÓN DEL PASSWORD DEL FOTÓGRAFO DEBE TENER UN MÍNIMO DE 6 CARACTERES Y UN MÁXIMO DE 16")
    @Password(message = "LA CONFIRMACIÓN DEL PASSWORD DEL FOTÓGRAFO DEBE TENER AL MENOS 1 CARÁCTER EN MAYÚSCULA, 1 CARÁCTER EN MINÚSCULA, 1 NÚMERO Y 1 CARÁCTER ESPECIAL (EJEMPLO: 1Password$)")
    @JsonProperty("confirmPassword")
    private String confirmPassword;
}