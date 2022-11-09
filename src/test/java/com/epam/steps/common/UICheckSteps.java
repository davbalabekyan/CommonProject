package com.epam.steps.common;

import com.epam.pages.main.AdminPage;
import com.epam.pages.main.LoginPage;
import com.epam.pages.main.SuperAdminPage;
import com.epam.pages.popup.AcademicYearsAndVacationPopup;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class UICheckSteps {

    private LoginPage loginPage;
    private SuperAdminPage superAdminPage;
    private AdminPage adminPage;
    private AcademicYearsAndVacationPopup yearsAndVacationPopup;

    @Before
    public void initPages() {
        loginPage = new LoginPage();
        superAdminPage = new SuperAdminPage();
        adminPage = new AdminPage();
        yearsAndVacationPopup = new AcademicYearsAndVacationPopup();
    }

    @Then("Check all elements are present in login page")
    public void checkAllElementsArePresentInLoginPage() {
        assertThat(loginPage.checkAllElementsArePresent())
                .withFailMessage("All elements are present in login page")
                .isTrue();
    }

    @Then("Check all elements are present in super admin page")
    public void checkAllElementsArePresentInSuperAdminPage() {
        assertThat(superAdminPage.checkAllElementsArePresent())
                .withFailMessage("All elements are not present in super admin page.")
                .isTrue();
    }

    @Then("Check all elements are present on admin page")
    public void seeAllElementsArePresentOnAdminPage() {
        assertThat(adminPage.checkAllElementsArePresent())
                .withFailMessage("Elements in admin page are not present")
                .isTrue();
    }

    @Then("Check all elements are present on the chosen section")
    public void checkAllElementsArePresentInTheSection() {
        assertThat(adminPage.checkUIOfSection())
                .withFailMessage("Elements are not present in selected section")
                .isTrue();
    }

    @Then("Check all fields are present in create popup years section")
    public void checkAllFieldsArePresentInCreatePopupYearsSection() {
        assertThat(yearsAndVacationPopup.checkUIOfCreatePopupYearsSection())
                .withFailMessage("All required elements in years section are not displayed")
                .isTrue();
    }
}
