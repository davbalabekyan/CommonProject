package com.epam.steps.admin;

import com.epam.helpers.SharedTestData;
import com.epam.pages.main.AdminPage;
import com.epam.pages.popup.CreatePopup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminSteps {
    private final AdminPage adminPage = new AdminPage();
    private final CreatePopup createPopup = new CreatePopup();
    private String password;

    @Then("See all elements are present")
    public void seeAllElementsArePresent() {
        assertThat(adminPage.checkAllElementsArePresent()).isTrue();
    }

    @When("Select {} section")
    public void selectTeachersSection(String section) {
        adminPage.selectSection(section);
    }

    @And("Click on create button on admin page")
    public void clickOnCreateButtonOnAdminPage(){
        adminPage.clickOnCreateButton();
    }

    @And("Get value of generated password")
    public void getValueOfGeneratedPassword() {
        password = SharedTestData.getLastGeneratedPassword();
    }

    @Then("See that generated password has been changed")
    public void seeThatGeneratedPasswordHasBeenChanged() {
        assertThat(SharedTestData.getLastGeneratedPassword()).isNotEqualTo(password);
    }

    @And("Generate button is active")
    public void generateButtonIsActive() {
        assertThat(createPopup.checkGeneratePasswordButtonIsEnabled()).isTrue();
    }
    @And("Type in input fields more than 50 symbols")
    public void typeInInputFieldsMoreThanSymbols() {
        createPopup.fillNameWithMoreSymbols();
        createPopup.fillSurnameWithMoreSymbols();
        createPopup.fillEmailWithMoreSymbols();
    }

    @And("Password structure comply with the given requirements")
    public void passwordStructureComplyWithTheGivenRequirements() throws InterruptedException {
        assertThat(createPopup.checkGeneratedPasswordStructure()).isTrue();
    }

    @And("Fill in existed name, surname")
    public void fillInNameSurnameAndAnExistedEmail() {
        createPopup.fillExistedNameAndSurname();
    }

    @Then("Fill in password field no interaction happens because password field is read only")
    public void fillInPasswordFieldNoInteractionHappensBecausePasswordFieldIsReadOnly() {
        createPopup.fillPassword("myPassword");
        assertThat(createPopup.checkThePasswordFieldIsDisabled()).isTrue();
    }

    @Then("Teacher is created and displayed in teachers section")
    public void teacherIsCreatedAndAddedToTeachersList() {
        assertThat(adminPage.checkNewTeacherIsDisplayedOnAdminsSection()).isTrue();
    }
    @And("Fill non-existed email")
    public void fillNonExistedEmail() {
        createPopup.fillEmail(System.currentTimeMillis() + "@gmail.com");
    }

    @Then("Check admin is not added in the DB")
    public void checkAdminIsNotAddedInTheDB() {
        assertThat(adminPage.checkAdminIsNotAddedInTheDB())
                .isTrue();
    }
}
