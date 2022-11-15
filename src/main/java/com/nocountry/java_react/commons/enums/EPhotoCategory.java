package com.nocountry.java_react.commons.enums;

public enum EPhotoCategory {

    DOCUMENTARY("Documentary"),
    LANDSCAPES("Landscapes"),
    URBAN("Urban"),
    ANIMALS("Animals"),
    MACRO("Macro"),
    PORTRAIT("Portrait"),
    ABSTRACT("Abstract"),
    ADVERTISING("Advertising"),
    PRODUCTS("Products");

    private final String categoryString;

    EPhotoCategory(String categoryString) {
        this.categoryString = categoryString;
    }

    @Override
    public String toString() {
        return categoryString;
    }

    public static EPhotoCategory typeOrValue(String value) {
        for(EPhotoCategory type : EPhotoCategory.class.getEnumConstants()) {
            if(type.toString().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("EPhotoCategory NOT FOUND");
    }
}