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

    public void fillEmail(String email) {
        logger.info("Fill email {}", email);
        uiHelper.sendKeys(emailInput, email);
    }

    public void fillPassword(String password) {
        logger.info("Fill password {}", password);
        uiHelper.sendKeys(passwordInput, password);
    }

    public void clickOnLoginButton() {
        logger.info("Click on login button");
        uiHelper.clickOnWebElement(loginButton);
    }

    public void enterLastGeneratedEmail() {
        String email = SharedTestData.getLastGeneratedEmail();
        logger.info("Fill last generated email {}", email);
        uiHelper.sendKeys(emailInput, email);
    }

    public void enterLastGeneratedPassword() {
        String password = SharedTestData.getLastGeneratedPassword();
        logger.info("Fill last generated password {}", password);
        uiHelper.sendKeys(passwordInput, password);
    }

    public String getErrorMessage() {
        String errMessage = errorMessage.getText();
        logger.info("Get error message - {}", errMessage);
        return errMessage;
    }

    public boolean checkAllElementsArePresent() {
        logger.info("Check elements email, password, login button are displayed in login page");
        return uiHelper.checkElementsAreDisplayed(emailInput, passwordInput, loginButton);
    }

    @Override
    protected String pageUrl() {
        return "http://localhost:8082/login";
    }
}
