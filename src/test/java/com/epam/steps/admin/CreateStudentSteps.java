package com.epam.steps.admin;

import com.epam.pages.main.AdminPage;
import com.epam.pages.popup.CreatePopup;
import com.epam.pages.popup.StudentsPopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateStudentSteps extends BaseSteps {
    private final AdminPage adminPage = new AdminPage();
    private final StudentsPopup studentsPopup = new StudentsPopup();
    private final CreatePopup createPopup = new CreatePopup();

    @Then("See all elements are present in student section")
    public void seeAllElementsArePresentInStudentSection() {
        assertThat(adminPage.checkUIofStudentsSection())
                .withFailMessage("Elements are not present in student section")
                .isTrue();
    }

    @Then("Check all fields are present in create popup - students section")
    public void checkAllFieldsArePresentInCreatePopupStudentsSection() {
        assertThat(studentsPopup.checkUIOfCreatePopupStudentsSection())
                .withFailMessage("Elements are not present in create popup - student section")
                .isTrue();
    }

    @And("Fill in all required fields in students section create popup")
    public void fillInAllRequiredFieldsInStudentsSectionCreatePopup() {
        createPopup.fillNameSurnameEmail();
        createPopup.clickOnGeneratePasswordButton();
        studentsPopup.fillBloodGenderBirthdayAddress();
    }

    @Then("Check all input fields are empty in students section create popup")
    public void checkAllInputFieldsAreEmptyInStudentsSectionCreatePopup() {
        assertThat(studentsPopup.checkAllFieldsAreEmptyInStudentsCreatePopup())
                .withFailMessage("All input fields are not empty in create popup - student section")
                .isTrue();
    }

    @When("Fill in existed name, surname, all other required fields besides email")
    public void fillInExistedNameSurnameNonExistedMailAllOtherRequiredFields() {
        createPopup.fillExistedName();
        createPopup.fillExistedSurname();
        studentsPopup.fillBloodGenderBirthdayAddress();
    }

    @When("Click on 'Birth Day' field opens calendar")
    public void clickOnBirthDayFieldOpensCalendar() {
        assertThat(studentsPopup.checkCalendarIsOpened())
                .withFailMessage("Calendar is not opened in create popup - students section")
                .isTrue();
    }

    @Then("User is able to select dates between the given interval provided by documentation")
    public void userIsAbleToSelectDatesBetweenTheGivenIntervalProvidedByDocumentation() {
        assertThat(studentsPopup.isYearFromInterval())
                .withFailMessage("Selected year is not from the given interval provided by documentation")
                .isTrue();
    }

    @And("The user choice is displayed in the {} field")
    public void theUserChoiceIsDisplayedInTheLinkedClassField(String fieldName) {
        assertThat(studentsPopup.checkValueOfSelectedField(fieldName))
                .withFailMessage("Saved value of selected fields is not the same with what user sees on screen")
                .isTrue();
    }

    @Then("Check student created by admin is not added in the DB")
    public void checkAdminIsNotAddedInTheDB() {
        assertThat(dbHelper.isUserAddedInTheDB())
                .withFailMessage("Student wasn't meant to be added in the DB, but it was added.")
                .isTrue();
    }

    @Then("Check student created by admin is added in the DB")
    public void checkAdminIsAddedInTheDB() {
        assertThat(dbHelper.isUserAddedInTheDB())
                .withFailMessage("Student was meant to be added in the DB, but wasn't added.")
                .isFalse();
    }

    @When("Click on {} field having drop-down list and select {} from the list")
    public void clickOnFieldAndSelectAcademicClassNameFromTheList(String fieldName, String valueToSelect) {
        studentsPopup.clickOnFieldAndSelectValue(fieldName, valueToSelect);
    }

    @And("Check that 'Linked Parent' and 'Linked Class' fields are not selected")
    public void checkThatLinkedParentAndLinkedClassFieldsAreNotSelected() {
        assertThat(studentsPopup.areLinkedClassAndParentSelected())
                .withFailMessage("'Linked Parent' and 'Linked Class' fields are selected, but they shouldn't be.")
                .isFalse();
    }


    @Then("Check the student password is hashed in the DB")
    public void checkTheStudentPasswordIsHashedInTheDB() {
        assertThat(dbHelper.isStudentPasswordHashed())
                .withFailMessage("Password was not hashed in the DB.")
                .isTrue();
    }

}
