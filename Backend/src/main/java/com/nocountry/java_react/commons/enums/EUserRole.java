package com.nocountry.java_react.commons.enums;

public enum EUserRole {

    ADMIN("Admin"),
    PHOTOGRAPHER("Photographer"),
    BUYER("Buyer");

    private final String roleString;

    EUserRole(String roleString) {
        this.roleString = roleString;
    }

    @Override
    public String toString() {
        return roleString;
    }

    public static EUserRole typeOrValue(String value) {
        for (EUserRole type : EUserRole.class.getEnumConstants()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("EUserRole NOT FOUND");
    }
}