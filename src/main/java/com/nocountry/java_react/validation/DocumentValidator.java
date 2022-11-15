package com.nocountry.java_react.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class DocumentValidator implements ConstraintValidator<Document, String> {

    private static final String REGEX = "^\\d+$";

    @Override
    public void initialize(Document constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(REGEX);
        return StringUtils.hasText(value) && pattern.matcher(value).matches();
    }
}