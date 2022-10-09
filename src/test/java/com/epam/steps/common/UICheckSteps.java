package com.epam.steps.common;

import com.epam.pages.main.LoginPage;
import com.epam.pages.main.SuperAdminPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;

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
}
