package com.epam.steps.common;

import com.epam.pages.main.LoginPage;
import com.epam.pages.main.SuperAdminPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class UICheckSteps {

    private SuperAdminPage superAdminPage;
    private LoginPage loginPage;

    @Before
    public void initPages() {
        superAdminPage = new SuperAdminPage();
        loginPage = new LoginPage();
    }

    @Then("Check all elements are present in super admin page")
    public void checkAllElementsArePresentInSuperAdminPage() {
        assertThat(superAdminPage.checkAllElementsArePresent())
                .isTrue();
    }

    @Then("Check all elements are present in login page")
    public void checkAllElementsArePresentInLoginPage() {
        assertThat(loginPage.checkAllElementsArePresent())
                .isTrue();
    }

    @Then("The user sees email, password and login button are present")
    public void theUserSeesEmailPasswordAndLoginButtonArePresent() {
        Assertions.assertThat(loginPage.checkAllElementsArePresent()).isTrue();
    }
}
