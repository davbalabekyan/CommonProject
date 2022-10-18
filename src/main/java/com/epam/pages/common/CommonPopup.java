package com.epam.pages.common;

import com.epam.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonPopup extends BasePage {

    @FindBy(id = "submit")
    protected WebElement saveButton;
    @FindBy(className = "close-btn")
    protected WebElement xButton;

    public void clickOnSaveButton() {
        logger.info("Click on save button");
        uiHelper.clickOnWebElement(saveButton);
    }

    public void clickOnXButton() {
        logger.info("Click on X button button");
        uiHelper.clickOnWebElement(xButton);
    }
}
