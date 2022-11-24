package com.nocountry.java_react.exception;

import java.io.Serial;

public class PhotographerException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public PhotographerException(String message) {
        super(message);
    }
}