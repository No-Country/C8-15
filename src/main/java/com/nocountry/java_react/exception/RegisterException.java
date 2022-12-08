package com.nocountry.java_react.exception;

import java.io.Serial;

public class RegisterException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RegisterException(String message) {
        super(message);
    }
}