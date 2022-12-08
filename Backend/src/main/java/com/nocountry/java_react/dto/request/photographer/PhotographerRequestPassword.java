package com.nocountry.java_react.dto.request.photographer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.java_react.validation.Password;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PhotographerRequestPassword {

    @NotBlank(message = "EL PASSWORD ANTERIOR DEL FOTÓGRAFO NO PUEDE ESTAR VACÍA O SER NULA")
    @Size(min = 6, max = 16, message = "EL PASSWORD ANTERIOR DEL FOTÓGRAFO DEBE TENER UN MÍNIMO DE 6 CARACTERES Y UN MÁXIMO DE 16")
    @Password(message = "EL PASSWORD ANTERIOR DEL FOTÓGRAFO DEBE TENER AL MENOS 1 CARÁCTER EN MAYÚSCULA, 1 CARÁCTER EN MINÚSCULA, 1 NÚMERO Y 1 CARÁCTER ESPECIAL (EJEMPLO: 1Password$)")
    @JsonProperty("oldPassword")
    private String oldPassword;

    @NotBlank(message = "EL PASSWORD DEL FOTÓGRAFO NO PUEDE ESTAR VACÍA O SER NULA")
    @Size(min = 6, max = 16, message = "EL PASSWORD DEL FOTÓGRAFO DEBE TENER UN MÍNIMO DE 6 CARACTERES Y UN MÁXIMO DE 16")
    @Password(message = "EL PASSWORD DEL FOTÓGRAFO DEBE TENER AL MENOS 1 CARÁCTER EN MAYÚSCULA, 1 CARÁCTER EN MINÚSCULA, 1 NÚMERO Y 1 CARÁCTER ESPECIAL (EJEMPLO: 1Password$)")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "LA CONFIRMACIÓN DEL PASSWORD DEL FOTÓGRAFO NO PUEDE ESTAR VACÍA O SER NULA")
    @Size(min = 6, max = 16, message = "LA CONFIRMACIÓN DEL PASSWORD DEL FOTÓGRAFO DEBE TENER UN MÍNIMO DE 6 CARACTERES Y UN MÁXIMO DE 16")
    @Password(message = "LA CONFIRMACIÓN DEL PASSWORD DEL FOTÓGRAFO DEBE TENER AL MENOS 1 CARÁCTER EN MAYÚSCULA, 1 CARÁCTER EN MINÚSCULA, 1 NÚMERO Y 1 CARÁCTER ESPECIAL (EJEMPLO: 1Password$)")
    @JsonProperty("confirmPassword")
    private String confirmPassword;
}
