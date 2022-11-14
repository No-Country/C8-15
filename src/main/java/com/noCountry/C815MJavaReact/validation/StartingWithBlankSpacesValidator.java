package com.noCountry.C815MJavaReact.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class StartingWithBlankSpacesValidator implements ConstraintValidator<StartingWithBlankSpaces, String> {

    private static final String REGEX = "^\\S\\w*(.?)+$";

    @Override
    public void initialize(StartingWithBlankSpaces constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(REGEX);
        return StringUtils.hasText(value) && pattern.matcher(value).matches();
    }
}