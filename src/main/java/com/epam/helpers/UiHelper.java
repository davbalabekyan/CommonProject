package com.epam.helpers;

import org.openqa.selenium.WebElement;

public class UiHelper {

    private final WaitHelper waitHelper = new WaitHelper();

    public void clickOnWebElement(final WebElement target) {
        waitHelper.waitElementToBeClickable(target);
        target.click();
    }

    public void sendKeys(final WebElement target, final String text) {
        waitHelper.waitElementToBeVisible(target);
        target.sendKeys(text);
    }
}
