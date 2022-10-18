package com.epam.helpers;

public enum ErrorMessages {

    INVALID_EMAIL("Email is invalid"),
    MORE_THAN_50_SYMBOLS("Symbols cant be more than 50"),
    BLANK_INPUT_FIELDS("Please, fill the required fields"),
    INCORRECT_LOGIN_OR_PASSWORD("Incorrect email and/or password"),
    EXISTED_EMAIL("A user with the specified email already exists");

    private final String errorMessage;

    ErrorMessages(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
