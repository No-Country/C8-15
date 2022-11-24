package com.nocountry.java_react.exception;

import java.io.Serial;

public class GlobalException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public GlobalException(String message) {
        super(message);
    }
}