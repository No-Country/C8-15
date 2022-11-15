package com.nocountry.java_react.commons.enums;

public enum EPhotoCategory {

    DOCUMENTARY("Documentary"),
    LANDSCAPES("Landscapes"),
    URBAN("Urban"),
    ANIMALS("Animals"),
    MACRO("Macro"),
    PORTRAIT("Portrait"),
    ABSTRACT("Abstract"),
    ASTROPHOTOGRAPHY("Astrophotography"),
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
}
