package com.nocountry.java_react.commons.enums;

public enum EPathUpload {

    CREATE_PHOTO_FOLDER("src/main/resources/static/fileUploads/photo"),
    PATH_PHOTO_IMAGE("/fileUploads/photo/"),
    CREATE_PHOTOGRAPHER_FOLDER("src/main/resources/static/fileUploads/photographer"),
    PATH_PHOTOGRAPHER_IMAGE("/fileUploads/photographer/"),
    CREATE_BUYER_FOLDER("src/main/resources/static/fileUploads/buyer"),
    PATH_BUYER_IMAGE("/fileUploads/buyer/");

    private final String pathFileUploadString;

    EPathUpload(String pathFileUploadString) {
        this.pathFileUploadString = pathFileUploadString;
    }

    @Override
    public String toString() {
        return pathFileUploadString;
    }

    public static EPathUpload typeOrValue(String value) {
        for (EPathUpload type : EPathUpload.class.getEnumConstants()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("EPathUpload NOT FOUND");
    }
}