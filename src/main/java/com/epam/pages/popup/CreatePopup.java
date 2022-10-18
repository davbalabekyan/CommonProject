package com.epam.pages.popup;

import com.epam.helpers.SharedTestData;
import com.epam.jdbc.service.UserServiceImpl;
import com.epam.pages.common.CommonPopup;
import com.epam.pages.main.SuperAdminPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.epam.helpers.ErrorMessages.*;

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
    @FindBy(xpath = "//input[@class='form_input' and not(@readonly)]/following-sibling::div[not(preceding-sibling::input[@id='date'])]")
    protected List<WebElement> errorMessagesOfMoreSymbols;
    @FindBy(id = "popup-container")
    protected WebElement popupWindow;
    @FindBy(xpath = "//input[not(@readonly)]")
    protected List<WebElement> inputFields;

    public void fillName(String name) {
        uiHelper.sendKeys(nameInput, name);
    }

    public void fillExistedName() {
        String name = new SuperAdminPage().getNameOfLastCreatedUser();
        fillName(name);
    }

    public void fillSurname(String surname) {
        uiHelper.sendKeys(surnameInput, surname);
    }

    public void fillExistedSurname() {
        String surname = new SuperAdminPage().getSurnameOfLastCreatedUser();
        fillSurname(surname);
    }

    public void fillEmail(String email) {
        logger.info("Fill email {}", email);
        uiHelper.sendKeys(emailInput, email);
    }

    public void fillNonExistedEmail() {
        String email = String.format("%s@gmail.com", RandomStringUtils.random(7, true, true));
        logger.info("Fill non-existed email {}", email);
        if (new UserServiceImpl().findByEmail(email).getEmail() == null) {
            fillEmail(email);
        } else {
            fillNonExistedEmail();
        }
    }

    public void saveEmailValue() {
        SharedTestData.setLastGeneratedEmail(emailInput.getDomProperty("value"));
    }

    public void saveNameAndSurnameValue() {
        SharedTestData.setNameField(nameInput.getDomProperty("value"));
        SharedTestData.setSurnameField(surnameInput.getDomProperty("value"));
    }

    public void fillInputFieldsWithMoreSymbols() {
        String generatedString = RandomStringUtils.random(51, true, true);
        logger.info("50 symbols in name field are {}", generatedString);
        inputFields
                .forEach(fields -> uiHelper.sendKeys(fields, generatedString));
    }

    public void fillExistedEmail() {
        uiHelper.sendKeys(emailInput, "petrosyan@gmail.com");
    }

    public void fillNameSurnameEmail() {
        fillName("Davit");
        fillSurname("Balabekyan");
        String generatedString = RandomStringUtils.random(7, true, true);
        fillEmail(String.format("%s@gmail.com", generatedString));
    }

    public void clickOnGeneratePasswordButton() {
        uiHelper.clickOnWebElement(generatePasswordButton);
    }

    public boolean checkAllFieldsArePresent() {
        logger.info("Check fields name, surname, email, password, save button," +
                "generate password button, X button are displayed in create popup");
        return uiHelper.checkElementsAreDisplayed(
                nameInput,
                surnameInput,
                emailInput,
                passwordInput,
                saveButton,
                generatePasswordButton,
                xButton
        );
    }

    public boolean checkAllInputFieldsAreEmpty() {
        logger.info("Check fields name, surname, email, password are displayed in create popup");
        return uiHelper.checkElementsAreEmpty(
                nameInput,
                surnameInput,
                emailInput,
                passwordInput
        );
    }

    public boolean checkGeneratedPasswordIsFilled() {
        logger.info("Check password field is filled");
        return !passwordInput.getDomProperty("value").isEmpty();
    }

    public boolean checkGeneratePasswordButtonIsEnabled() {
        logger.info("Check 'Generate password' button is active");
        return generatePasswordButton.isEnabled();
    }

    public boolean checkGeneratedPasswordStructure() {
        logger.info("Check generated password structure");
        return passwordInput.getDomProperty("value")
                .matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#!%*?&])[A-Za-z\\d()`~@$?!\"'^#*:.,;<>%-_\\[\\]+=|/{}&]{9,50}");
    }

    public boolean checkThePasswordFieldIsDisabled() {
        logger.info("Check the password field is readonly");
        return Boolean.parseBoolean(passwordInput.getAttribute("readonly"));
    }

    public void savePasswordValue() {
        SharedTestData.setLastGeneratedPassword(passwordInput.getDomProperty("value"));
    }

    public boolean passwordIsChanged() {
        logger.info("Check password is changed after generating a new password");
        return !passwordInput
                .getDomProperty("value")
                .equals(SharedTestData.getLastGeneratedPassword());
    }

    public String getInvalidEmailErrorMessage() {
        logger.info("Get error message of invalid inputted email");
        return emailInvalidAndExistedErrorMessage.getText();
    }

    public boolean checkErrorMessagesOfBlankInputFields() {
        logger.info("Check error messages of blank input fields");
        return errorMessagesOfBlankInputFields
                .stream()
                .allMatch(errMessage -> errMessage.getText()
                        .equals(BLANK_INPUT_FIELDS.getErrorMessage()));
    }

    public boolean checkErrorMessagesOfMoreSymbolsFilledInputFields() {
        logger.info("Check error messages for inputting more than 50 symbols");
        return errorMessagesOfMoreSymbols
                .stream()
                .allMatch(errMessage -> errMessage.getText()
                        .equals(MORE_THAN_50_SYMBOLS.getErrorMessage()));
    }

    public String getErrorMessageOfExistedEmail() {
        logger.info("Get error message of existed email");
        return emailInvalidAndExistedErrorMessage.getText();
    }

    public boolean popupIsClosed() {
        logger.info("Popup is closed");
        return !popupWindow.isDisplayed();
    }
}
