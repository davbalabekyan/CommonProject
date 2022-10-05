package com.epam.helpers;

public class SharedTestData {

    private static String generatedPassword;

    public static void setLastGeneratedPassword(String password) {
        generatedPassword = password;
    }

    public static String getLastGeneratedPassword() {
        return generatedPassword;
    }
}
