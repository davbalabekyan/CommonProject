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
import io.cucumber.java.en.When;

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

    @Before
    public void initPages() {
        loginPage = new LoginPage();
        adminPage = new AdminPage();
        createPopup = new CreatePopup();
        superAdminPage = new SuperAdminPage();
        loginPage.goToPage();
    }

    @Given("Enter email {} in login page")
    public void fillEmailInLoginPage(String email) {
        loginPage.fillEmail(email);
    }

    @And("Enter password {} in login page")
    public void fillPasswordInLoginPage(String password) {
        loginPage.fillPassword(password);
    }

    @And("Click on 'login' button")
    public void clickOnLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @Then("The user is on {} page")
    public void theUserIsOnRequestedPage(String roleName) {
        assertThat(roleName).isEqualToIgnoringCase(superAdminPage.getRoleName());
    }

    @When("Leave blank or incorrect {} and-or {} field")
    public void leaveBlankOrIncorrectUsernameAndOrPasswordField(String email, String password) {
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
    }

    @Then("See error message")
    public void seeErrorMessage() {
       assertThat(loginPage.getErrorMessage()).isEqualTo("Incorrect email and/or password");
    }

    @And("Fill in all required fields")
    public void fillInAllRequiredFields() {
        createPopup.fillAllFields();
        createPopup.doubleClickOnGeneratePasswordButton();
    }

    @Then("Sign in as admin with generated password")
    public void signInAsAdminWithGeneratedPassword() {
        loginPage.goToPage();
        loginPage.enterLastGeneratedEmail();
        loginPage.enterLastGeneratedPassword();
        loginPage.clickOnLoginButton();
        assertThat(createPopup.passwordIsChanged()).isTrue();
        assertThat(adminPage.getNameAndSurname()).contains(SharedTestData.getNameField());
    }

    @AfterAll
    public static void tearDown() {
        quitDriver();
    }
}
