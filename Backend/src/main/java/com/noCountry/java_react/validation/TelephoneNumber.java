package com.nocountry.java_react.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelephoneNumberValidator.class)
public @interface TelephoneNumber {

    String STRING_MESSAGE = "EL NÚMERO DE TELÉFONO DEBE TENER EL FORMATO CORRECTO." +
            "POR EJEMPLO: " +
            "0000000000 (SIN ESPACIOS ENTRE NÚMEROS)" +
            "000 000 0000 O 0000 00 0000 (CON ESPACIOS ENTRE NÚMEROS)" +
            "000-000-0000 O 0000-00-0000 (SEPARAOS POR -)" +
            "000_000_0000 O 0000_00_0000 (SEPARAOS POR _)";

    String message() default STRING_MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}