package com.epam.helpers;

public class SharedTestData {

    private static String generatedPassword;
    private static String lastGeneratedEmail;
    private static String nameField;
    private static String surnameField;

    public static void setLastGeneratedPassword(String password) {
        generatedPassword = password;
    }

    public static String getLastGeneratedPassword() {
        return generatedPassword;
    }

    public static void setLastGeneratedEmail(String email) {
        lastGeneratedEmail = email;
    }

    public static String getLastGeneratedEmail() {
        return lastGeneratedEmail;
    }

    public static void setNameField(String name) {
        nameField = name;
    }

    public static String getNameField() {
        return nameField;
    }

    public static void setSurnameField(String surname) {
        surnameField = surname;
    }

    public static String getSurnameField() {
        return surnameField;
    }

    public static String getMoreSymbols() {
        return "qawsedrftgyhujikolpzxcvbnmqawsedrfgthyjukiopzxcvcdrtdrscvhhg";
    }

    public static String getInvalidEmail() {
        return "!!InvalidEmail.com";
    }

    public static String getExistedEmail() {
        return "petrosyan@gmail.com";
    }
}
