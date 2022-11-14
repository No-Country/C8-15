package com.noCountry.C815MJavaReact.commons.enums;

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
}