package com.noCountry.C815MJavaReact.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class TelephoneNumberValidator implements ConstraintValidator<TelephoneNumber, String> {

    private static final String REGEX = "^\\d{3}[\\s-_]?\\d{3}[\\s-_]?\\d{4}|\\d{4}[\\s-_]?\\d{2}[\\s-_]?\\d{4}$";

    @Override
    public void initialize(TelephoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(REGEX);
        return StringUtils.hasText(value) && pattern.matcher(value).matches();
    }
}