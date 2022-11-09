package com.epam.steps.admin;

import com.epam.helpers.ErrorMessagesProvider;
import com.epam.helpers.UserDataProvider;
import com.epam.pages.main.AdminPage;
import com.epam.pages.popup.SubjectPopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateSubjectSteps extends BaseSteps {

    private SubjectPopup subjectPopup;
    private AdminPage adminPage;

    @Before
    public void initPages() {
        subjectPopup = new SubjectPopup();
        adminPage = new AdminPage();
    }

    @Given("Fill subject name")
    public void fillSubjectName() {
        subjectPopup.fillSubjectName();
    }

    @Given("Fill name of teacher {}")
    public void fillNameOfSubject(String subjectName) {
        subjectPopup.fillTeacherName(subjectName);
    }

    @Given("Fill existed subject name")
    public void fillExistedSubjectName() {
        subjectPopup.fillExistedSubjectName();
    }

    @Given("Click on the 'Teachers' drop-down list")
    public void clickOnTheTeachersDropDownList() {
        subjectPopup.clickOnTheTeachersDropDownList();
    }

    @And("Select teacher {}")
    public void selectTeacherByName(String teacherName) {
        subjectPopup.selectTeacherByName(teacherName);
    }

    @And("Select teacher")
    public void selectTeacher() {
        subjectPopup.selectTeacher();
    }

    @And("Click on 'X' button of the selected teacher")
    public void clickOnXButtonOfTheSelectedTeacher() {
        subjectPopup.clickOnXButtonOfSelectedTeacher();
    }

    @And("Click on 'X' button of the teacher list")
    public void clickOnXButtonOfTheTeacherList() {
        subjectPopup.clickOnXButtonOfTheTeacherList();
    }


    @And("Save value from subject name field")
    public void saveValueFromSubjectNameField() {
        subjectPopup.saveValueFromSubjectNameField();
    }

    @And("Save value of selected teacher")
    public void saveValueOfSelectedTeacher() {
        subjectPopup.saveValueOfSelectedTeacher();
    }

    @Then("Check all elements are present in create popup - subjects section")
    public void checkAllElementsArePresentInCreatePopupInSubjectsSection() {
        assertThat(subjectPopup.checkAllElementsArePresentInCreatePopup())
                .withFailMessage("Elements are not present on the chosen section")
                .isTrue();
    }

    @Then("Check all fields and drop down list are empty in subjects section create popup")
    public void checkAllFieldsAndDropDownListAreEmptyInSubjectsSectionCreatePopup() {
        assertThat(subjectPopup.checkAllFieldsAndDropDownListAreEmptyInSubjectsSectionCreatePopup())
                .withFailMessage("Fields and drop down list are not empty on the subjects section")
                .isTrue();
    }

    @Then("Check subject is not added in the DB")
    public void checkSubjectIsNotAddedInTheDB() {
        logger.info("Check subject is not added in the DB");
        assertThat(dbHelper.isSubjectAddedInTheDB())
                .withFailMessage("Subject wasn't meant to be added in the DB, but was added.")
                .isTrue();
    }

    @Then("Check subject is added in the DB")
    public void checkSubjectIsAddedInTheDB() {
        logger.info("Check subject is added in the DB");
        assertThat(dbHelper.isSubjectAddedInTheDB())
                .withFailMessage("Subject was meant to be added in the DB, but wasn't added.")
                .isFalse();
    }

    @Then("Check subject is displayed in the list")
    public void checkSubjectIsDisplayedInTheList() {
        assertThat(adminPage.checkNewCreatedItemIsDisplayedOnAdminsSection())
                .withFailMessage("Subject is not displayed in the list, but it should be")
                .isTrue();
    }

    @Then("Check error message of existed subject name")
    public void checkExistedSubjectNameErrorMessage() {
        logger.info("Check error message of existed subject name");
        assertThat(subjectPopup.getExistedSubjectNameErrorMessage())
                .withFailMessage("Error message of existed subject name is incorrect")
                .isEqualTo(ErrorMessagesProvider.getExistedSubjectNameErrMessage());
    }

    @Then("Check matched items appeared below the Search line")
    public void checkMatchedItemsAreAppearedBelowTheSearchLine() {
        assertThat(subjectPopup.checkMatchedItemsAppearedBelowTheSearchLine())
                .withFailMessage("Matched items are not appeared")
                .isTrue();
    }

    @Then("Check selected items are shown with the 'x' icon")
    public void checkSelectedItemIsShownWithTheXIcon() {
        assertThat(subjectPopup.checkSelectedItemsAreShownWithTheXIcon())
                .withFailMessage("Selected item is not shown")
                .isTrue();
    }

    @Then("Check selected items are deleted from drop-list fragment")
    public void checkSelectedItemIsDeletedFromDropListFragment() {
        logger.info("Check selected items are deleted from drop-list fragment");
        assertThat(subjectPopup.checkThereAreNoSelectedItems())
                .withFailMessage("Selected item is not deleted")
                .isTrue();
    }

    @Then("Check there is no selected item")
    public void checkThereIsNoSelectedItem() {
        logger.info("Check there is no selected item");
        assertThat(subjectPopup.checkThereAreNoSelectedItems())
                .withFailMessage("There is selected item")
                .isTrue();
    }

    @Then("Check the search line placeholder")
    public void checkTheSearchLinePlaceholder() {
        logger.info("Check the search line placeholder");
        assertThat(subjectPopup.getTheSearchLinePlaceholder())
                .withFailMessage("There is selected item")
                .isEqualTo(UserDataProvider.getTeacherSearchLinePlaceholder());
    }
}
