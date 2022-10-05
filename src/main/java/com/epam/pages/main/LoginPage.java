package com.epam.pages.main;

import com.epam.helpers.SharedTestData;
import com.epam.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(className = "button-login")
    private WebElement loginButton;
    @FindBy(className = "errorMsg")
    private WebElement errorMessage;

    public void enterEmail(String email) {
        uiHelper.sendKeys(emailInput, email);
    }

    public void enterPassword(String password) {
        uiHelper.sendKeys(passwordInput, password);
    }

    public void clickOnLoginButton() {
        uiHelper.clickOnWebElement(loginButton);
    }

    public void enterLastGeneratedPassword() {
        uiHelper.sendKeys(passwordInput, SharedTestData.getLastGeneratedPassword());
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public boolean checkAllElementsArePresent() {
        return uiHelper.checkElementsAreDisplayed(emailInput, passwordInput, loginButton);
    }

    @Override
    protected String pageUrl() {
        return "http://localhost:8082/login";
    }
}
