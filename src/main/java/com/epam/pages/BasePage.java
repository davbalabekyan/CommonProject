package com.epam.pages;

import com.epam.core.driver.Driver;
import com.epam.helpers.UiHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final UiHelper uiHelper = new UiHelper();

    public BasePage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void goToPage() {
        driver.get(pageUrl());
    }

    protected String pageUrl() {
        return "";
    }

    public String getCurrentPageURL() {
        return driver.getCurrentUrl();
    }
}
