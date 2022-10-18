package com.epam.steps.common;

import com.epam.helpers.SharedTestData;
import com.epam.pages.main.AdminPage;
import com.epam.pages.main.LoginPage;
import com.epam.pages.main.SuperAdminPage;
import com.epam.pages.popup.CreatePopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static com.epam.helpers.ErrorMessages.INCORRECT_LOGIN_OR_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps extends BaseSteps {
    private LoginPage loginPage;
    private AdminPage adminPage;
    private SuperAdminPage superAdminPage;
    private CreatePopup createPopup;

    @BeforeAll
    public static void setupDriver() {
        setup();
    }

    @AfterAll
    public static void tearDown() {
        quitDriver();
    }

    @Before
    public void initPages() {
        loginPage = new LoginPage();
        adminPage = new AdminPage();
        createPopup = new CreatePopup();
        superAdminPage = new SuperAdminPage();
        loginPage.goToPage();
    }

    @Given("Fill email {} in login page")
    public void fillEmailInLoginPage(String email) {
        loginPage.fillEmail(email);
    }

    @And("Fill password {} in login page")
    public void fillPasswordInLoginPage(String password) {
        loginPage.fillPassword(password);
    }

    @And("Click on 'login' button")
    public void clickOnLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @Then("The user is on {} page")
    public void theUserIsOnRequestedPage(String roleName) {
        assertThat(roleName)
                .withFailMessage("The user is not on demanded page as role name is different from expected.")
                .isEqualToIgnoringCase(superAdminPage.getRoleName());
    }

    @Given("Fill {} and {} fields")
    public void fillEmailAndPassword(String email, String password) {
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
    }

    @Then("Check error message")
    public void checkErrorMessage() {
        assertThat(loginPage.getErrorMessage())
                .withFailMessage("Error message for incorrect login/or password was different from expected one.")
                .isEqualTo(INCORRECT_LOGIN_OR_PASSWORD.getErrorMessage());
    }

    @And("Fill in all required fields")
    public void fillInAllRequiredFields() {
        createPopup.fillNameSurnameEmail();
        createPopup.clickOnGeneratePasswordButton();
    }

    @Then("Sign in as admin with generated password")
    public void signInAsAdminWithGeneratedPassword() {
        loginPage.goToPage();
        loginPage.enterLastGeneratedEmail();
        loginPage.enterLastGeneratedPassword();
        loginPage.clickOnLoginButton();
        assertThat(adminPage.getNameAndSurname())
                .withFailMessage("Last created admin's name and surname is not equal to the signed in admin's name and surname.")
                .contains(SharedTestData.getNameField(), SharedTestData.getSurnameField());
    }

    @And("Save values from name, surname and email fields")
    public void getAndSaveValuesFromRequiredFields() {
        createPopup.saveEmailValue();
        createPopup.savePasswordValue();
        createPopup.saveNameAndSurnameValue();
    }
}
