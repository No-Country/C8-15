package com.nocountry.java_react.exception;

import java.io.Serial;

public class PhotoException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public PhotoException(String message) {
        super(message);
    }
}