package com.epam.steps;

import com.epam.core.driver.Driver;

public class BaseSteps {

    public static void setup() {
        Driver.getDriver();
    }

    public static void quitDriver() {
        Driver.quitDriver();
    }
}
