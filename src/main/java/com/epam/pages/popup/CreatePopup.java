package com.epam.pages.popup;

import com.epam.helpers.SharedTestData;
import com.epam.pages.common.CommonPopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreatePopup extends CommonPopup {

    @FindBy(id = "name")
    protected WebElement nameInput;
    @FindBy(id = "surname")
    protected WebElement surnameInput;
    @FindBy(id = "email")
    protected WebElement emailInput;
    @FindBy(id = "password")
    protected WebElement passwordInput;
    @FindBy(id = "btn")
    protected WebElement generatePasswordButton;
    @FindBy(xpath = "//*[@id='email']/following-sibling::div/p")
    protected WebElement emailInvalidErrorMessage;
    @FindBy(xpath = "//input/following-sibling::div[@class='error']")
    protected List<WebElement> blankInputsErrorMessages;

    public void fillName(String name) {
        uiHelper.sendKeys(nameInput, name);
    }

    public void fillSurname(String surname) {
        uiHelper.sendKeys(surnameInput, surname);
    }

    public void fillEmail(String email) {
        uiHelper.sendKeys(emailInput, email);
    }

    public void clickOnGeneratePasswordButton() {
        uiHelper.clickOnWebElement(generatePasswordButton);
        SharedTestData.setLastGeneratedPassword(passwordInput.getDomProperty("value"));
    }

    public boolean checkAllFieldsArePresentInCreatePopup() {
        return uiHelper.checkElementsAreDisplayed(nameInput,
                surnameInput,
                emailInput,
                passwordInput,
                saveButton,
                generatePasswordButton,
                xButton);
    }

    public boolean checkAllInputFieldsAreEmptyInCreatePopup() {
        return uiHelper.checkElementsAreEmpty(nameInput,
                surnameInput,
                emailInput,
                passwordInput);
    }

    public boolean checkGeneratedPasswordIsFilled() {
        return !passwordInput.getDomProperty("value").isEmpty();
    }

    public boolean checkGeneratePasswordButtonIsEnabled() {
        return generatePasswordButton.isEnabled();
    }

    public boolean checkGeneratedPasswordStructure() {
        return passwordInput.getDomProperty("value")
                .matches("nothing");
    }

    public boolean checkThePasswordFieldIsDisabled() {
        return Boolean.parseBoolean(passwordInput.getAttribute("readonly"));
    }

    public String getErrorMessageByInputFieldName(String inputFieldName) {
        String xPath = String.format("//input[@id='%s']/following-sibling::*[@class='error']", inputFieldName);
        return driver.findElement(By.xpath(xPath)).getText();
    }

    public String getInvalidEmailErrorMessage() {
        return emailInvalidErrorMessage.getText();
    }

    public boolean checkErrorMessagesOfBlankInputFields() {
        return blankInputsErrorMessages
                .stream()
                .allMatch(errMessage -> errMessage.getText()
                        .equalsIgnoreCase("Please, fill the required fields"));
    }
}
