package com.nocountry.java_react.exception;

import java.io.Serial;

public class BuyerException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public BuyerException(String message) {
        super(message);
    }
}