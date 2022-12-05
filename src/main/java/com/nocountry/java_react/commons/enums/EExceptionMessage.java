package com.nocountry.java_react.commons.enums;

import java.text.MessageFormat;

public enum EExceptionMessage {

    ////////////////////////////////////////////////////////////////////////////////////////////
    // GENERALS EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    ID_NOT_FOUND("ID NOT FOUND"),
    REQUEST_WRONG_DATA("REQUEST WRONG DATA"),
    RESPONSE_WRONG_DATA("RESPONSE WRONG DATA"),
    EMAIL_ALREADY_EXISTS("EMAIL {0} ALREADY EXISTS"),
    PASSWORDS_DO_NOT_MATCH("PASSWORDS DO NOT MATCH"),
    WRONG_PASSWORD("WRONG PASSWORD"),
    OLD_PASSWORD_DOES_NOT_MATCH("OLD PASSWORD DOES NOT MATCH"),
    NEW_PASSWORDS_DO_NOT_MATCH("NEW PASSWORDS DO NOT MATCH"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // PHOTO EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    PHOTO_NOT_FOUND("PHOTO NOT FOUND"),
    THE_FOLDER_CANNOT_BE_INITIALIZED("THE FOLDER CANNOT BE INITIALIZED fileUploads"),
    THE_PHOTO_CANNOT_BE_SAVED("THE PHOTO CANNOT BE SAVED. ERROR "),
    PHOTO_DELETED("PHOTO DELETED"),
    ERROR_DELETING_PHOTO("ERROR DELETING PHOTO"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // PHOTOGRAPHER EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    PHOTOGRAPHER_NOT_FOUND("PHOTOGRAPHER NOT FOUND"),
    THE_LIST_OF_PHOTOGRAPHERS_IS_EMPTY("THE LIST OF PHOTOGRAPHERS IS EMPTY"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // BUYER EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    BUYER_NOT_FOUND("BUYER NOT FOUND"),
    THE_LIST_OF_BUYERS_IS_EMPTY("THE LIST OF BUYERS IS EMPTY"),
    YOU_MUST_FIRST_PURCHASE_THE_PHOTO_TO_DOWNLOAD_IT("YOU MUST FIRST PURCHASE THE PHOTO TO DOWNLOAD IT");

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