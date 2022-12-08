package com.nocountry.java_react.exception;

import java.io.Serial;

public class UserAuthenticationException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserAuthenticationException(String message) {
        super(message);
    }
}