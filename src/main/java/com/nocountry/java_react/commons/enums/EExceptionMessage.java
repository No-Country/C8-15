package com.nocountry.java_react.commons.enums;

import java.text.MessageFormat;

public enum EExceptionMessage {

    ////////////////////////////////////////////////////////////////////////////////////////////
    // GENERALS EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////

    ID_NOT_FOUND("ID NOT FOUND"),
    ID_ALREADY_EXISTS("ID ALREADY EXISTS"),
    PARAM_NOT_FOUND("PARAM_NOT_FOUND"),
    REQUEST_WRONG_DATA("INVALID REQUEST"),
    RESPONSE_WRONG_DATA("INVALID RESPONSE"),
    DOCUMENT_ALREADY_EXISTS("DOCUMENT {0} ALREADY EXISTS"),
    EMAIL_ALREADY_EXISTS("EMAIL {0} ALREADY EXISTS"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // PHOTO EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    PHOTO_NOT_FOUND("PHOTO NOT FOUND"),
    THE_FOLDER_CANNOT_BE_INITIALIZED("THE FOLDER CANNOT BE INITIALIZED fileUploads"),
    THE_PHOTO_CANNOT_BE_SAVED("THE PHOTO CANNOT BE SAVED. ERROR "),
    PHOTO_DELETED("PHOTO DELETED"),
    ERROR_DELETING_PHOTO("ERROR DELETING PHOTO"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // OTHER
    ////////////////////////////////////////////////////////////////////////////////////////////

    IMAGE_NOT_FOUND("IMAGE NOT FOUND");

    private final String messageString;

    EExceptionMessage(String messageString) {
        this.messageString = messageString;
    }

    public String getMessage() {
        return messageString;
    }

    public String getMessage(String stringObject) {
        return MessageFormat.format(messageString, stringObject);
    }

    @Override
    public String toString() {
        return messageString;
    }

    public static EExceptionMessage typeOrValue(String value) {
        for (EExceptionMessage type : EExceptionMessage.class.getEnumConstants()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("ENUM MESSAGE NOT FOUND");
    }
}