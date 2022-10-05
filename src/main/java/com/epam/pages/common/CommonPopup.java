package com.epam.pages.common;

import com.epam.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonPopup extends BasePage {

    @FindBy(tagName = "h1")
    protected WebElement title;
    @FindBy(id = "submit")
    protected WebElement saveButton;
    @FindBy(className = "close-btn")
    protected WebElement xButton;

    public String getTitle() {
        return title.getText();
    }

    public void clickOnSaveButton() {
        uiHelper.clickOnWebElement(saveButton);
    }

    public void clickOnXButton() {
        uiHelper.clickOnWebElement(xButton);
    }
}
