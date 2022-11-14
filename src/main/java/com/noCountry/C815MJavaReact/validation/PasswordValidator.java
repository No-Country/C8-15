package com.noCountry.C815MJavaReact.validation;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class PasswordValidator implements
        ConstraintValidator<Password, String> {

    private static final String REGEXP =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile(REGEXP);
        return StringUtils.hasText(name) && pattern.matcher(name).matches();
    }
}