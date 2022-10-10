package com.epam.pages.popup;

import com.epam.helpers.SharedTestData;
import com.epam.pages.common.CommonPopup;
import com.epam.pages.main.SuperAdminPage;
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
    protected WebElement emailInvalidAndExistedErrorMessage;
    @FindBy(xpath = "//input/following-sibling::div[@class='error']")
    protected List<WebElement> errorMessagesOfBlankInputFields;
    @FindBy(xpath = "//input[not(@id='password')]/following-sibling::div[@class='error']")
    protected List<WebElement> errorMessagesOfMoreSymbols;
    @FindBy(id = "popup-container")
    protected WebElement popupWindow;
    private String password;

    public void fillName(String name) {
        uiHelper.sendKeys(nameInput, name);
        SharedTestData.setNameField(name);
    }

    public void fillNameWithMoreSymbols() {
        uiHelper.sendKeys(nameInput, SharedTestData.getMoreSymbols());
    }

    public void fillSurname(String surname) {
        uiHelper.sendKeys(surnameInput, surname);
        SharedTestData.setSurnameField(surname);
    }

    public void fillSurnameWithMoreSymbols() {
        uiHelper.sendKeys(surnameInput, SharedTestData.getMoreSymbols());
    }

    public void fillEmail(String email) {
        uiHelper.sendKeys(emailInput, email);
        SharedTestData.setLastGeneratedEmail(email);
    }

    public void fillEmailWithMoreSymbols() {
        uiHelper.sendKeys(emailInput, SharedTestData.getMoreSymbols());
    }

    public void fillInvalidEmail() {
        uiHelper.sendKeys(emailInput, SharedTestData.getInvalidEmail());
    }

    public void fillExistedEmail() {
        uiHelper.sendKeys(emailInput, SharedTestData.getExistedEmail());
    }

    public void fillExistedName() {
        String name = new SuperAdminPage().getNameOfLastCreatedUser();
        uiHelper.sendKeys(nameInput, name);
        SharedTestData.setNameField(name);
    }

    public void fillExistedSurname() {
        String surname = new SuperAdminPage().getSurnameOfLastCreatedUser();
        uiHelper.sendKeys(surnameInput, surname);
        SharedTestData.setSurnameField(surname);
    }

    public void fillAllFields() {
        fillAllFieldsBesidesEmail();
        fillEmail(System.currentTimeMillis() + "@gmail.com");
    }

    private void clickOnGeneratePasswordButtonInternal() {
        uiHelper.clickOnWebElement(generatePasswordButton);
    }

    public void clickOnGeneratePasswordButton() {
        clickOnGeneratePasswordButtonInternal();
        SharedTestData.setLastGeneratedPassword(passwordInput.getDomProperty("value"));
        logger.info("Generated password is {} ", SharedTestData.getLastGeneratedPassword());
    }

    public void doubleClickOnGeneratePasswordButton() {
        clickOnGeneratePasswordButtonInternal();
        password = passwordInput.getDomProperty("value");
        clickOnGeneratePasswordButton();
    }

    public boolean checkAllFieldsArePresent() {
        return uiHelper.checkElementsAreDisplayed(nameInput,
                surnameInput,
                emailInput,
                passwordInput,
                saveButton,
                generatePasswordButton,
                xButton);
    }

    public boolean checkAllInputFieldsAreEmpty() {
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

    public void fillAllFieldsBesidesEmail() {
        fillName("David");
        fillSurname("Balabekyan");
    }

    public void fillPassword(String password) {
        uiHelper.sendKeys(passwordInput, password);
    }

    public boolean checkGeneratedPasswordStructure() {
        return passwordInput.getDomProperty("value")
                .matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#!%*?&])[A-Za-z\\d()`~@$!^#*%-_?+=|&]{9,50}");
    }

    public boolean checkThePasswordFieldIsDisabled() {
        return Boolean.parseBoolean(passwordInput.getAttribute("readonly"));
    }

    public boolean passwordIsChanged() {
        return !password.equals(SharedTestData.getLastGeneratedPassword());
    }

    public String getInvalidEmailErrorMessage() {
        return emailInvalidAndExistedErrorMessage.getText();
    }

    public boolean checkErrorMessagesOfBlankInputFields() {
        return errorMessagesOfBlankInputFields
                .stream()
                .allMatch(errMessage -> errMessage.getText()
                        .equals("Please, fill the required fields"));
    }

    public boolean checkErrorMessagesOfMoreSymbolsFilledInputFields() {
        return errorMessagesOfMoreSymbols
                .stream()
                .allMatch(errMessage -> errMessage.getText()
                        .equals("Symbols cant be more than 50"));
    }

    public String getErrorMessageOfExistedEmail() {
        return emailInvalidAndExistedErrorMessage.getText();
    }

    public boolean popupIsClosed() {
        return !popupWindow.isDisplayed();
    }
}
