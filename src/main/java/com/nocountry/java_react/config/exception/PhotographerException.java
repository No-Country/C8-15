package com.nocountry.java_react.config.exception;

import java.io.Serial;

public class PhotographerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PhotographerException(String message) {
        super(message);
    }
}