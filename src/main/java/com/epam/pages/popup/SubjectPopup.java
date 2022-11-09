package com.epam.pages.popup;

import com.epam.helpers.ErrorMessagesProvider;
import com.epam.helpers.SharedTestData;
import com.epam.pages.common.CommonPopup;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SubjectPopup extends CommonPopup {

    @FindBy(id = "name")
    private WebElement nameField;
    @FindBy(id = "teacher")
    private WebElement teacherSelect;
    @FindBy(xpath = "//*[@class='selection']/span")
    private WebElement selectTeacherBox;
    @FindBy(className = "select2-search__field")
    private WebElement teacherSelectField;
    @FindBy(xpath = "//*[@id='select2-teacher-container']/li")
    private List<WebElement> listOfSelectedTeachers;
    @FindBy(xpath = "//button[@class='select2-selection__choice__remove']")
    private List<WebElement> xButtonsOfSelectedTeachers;
    @FindBy(className = "select2-selection__clear")
    private WebElement xButtonOfTheTeacherSelectBox;
    @FindBy(xpath = "//*[@class='select2-results__options']/li")
    private List<WebElement> teachersInDropDownList;
    @FindBy(className = "error")
    private WebElement errorMessageForSubjectsPopup;

    public void fillSubjectName() {
        String subjectName = RandomStringUtils.random(16, true, false);
        logger.info("Fill subject name {}", subjectName);
        uiHelper.sendKeys(nameField, subjectName);
    }

    public void fillExistedSubjectName() {
        String existedName = SharedTestData.getLastCreatedItemName();
        logger.info("Fill existed subject name {}", existedName);
        uiHelper.sendKeys(nameField, existedName);
    }

    public void clickOnTheTeachersDropDownList() {
        uiHelper.clickOnWebElement(teacherSelectField);
    }

    public void fillTeacherName(String teacherName) {
        SharedTestData.setLastInputtedTeacherName(teacherName);
        logger.info("Fill teacher name {}", teacherName);
        uiHelper.sendKeys(teacherSelectField, teacherName);
    }

    public void selectTeacherByName(String teacherName) {
        Select select = new Select(teacherSelect);
        logger.info("Select teacher by name {}", teacherName);
        select.selectByVisibleText(teacherName);
    }

    public void selectTeacher() {
        logger.info("Select teacher");
        Select select = new Select(teacherSelect);
        select.selectByIndex(0);
    }

    public void saveValueFromSubjectNameField() {
        SharedTestData.setLastCreatedItemName(nameField.getDomProperty("value"));
    }

    public void saveValueOfSelectedTeacher() {
        SharedTestData.setLastCreatedItemName(listOfSelectedTeachers.get(listOfSelectedTeachers.size() - 1).getText());
    }

    public boolean checkAllElementsArePresentInCreatePopup() {
        return uiHelper.checkElementsAreDisplayed(
                title,
                xButton,
                saveButton,
                nameField,
                teacherSelect
        );
    }

    public boolean checkAllFieldsAndDropDownListAreEmptyInSubjectsSectionCreatePopup() {
        logger.info("Check all fields and drop-down list are empty in subject section create popup");
        return uiHelper.checkElementsAreEmpty(nameField) && checkThereAreNoSelectedItems();
    }

    public String getExistedSubjectNameErrorMessage() {
        return errorMessageForSubjectsPopup.getText();
    }

    public boolean checkMatchedItemsAppearedBelowTheSearchLine() {
        logger.info("Check matched items are appeared below the search line");
        return teachersInDropDownList.stream()
                .allMatch(
                        teacherName -> teacherName.getText().toLowerCase()
                                .contains(SharedTestData.getLastInputtedTeacherName().toLowerCase())
                );
    }

    public boolean checkSelectedItemsAreShownWithTheXIcon() {
        logger.info("Check selected items are shown with the 'x' icon");
        return listOfSelectedTeachers.stream()
                .allMatch(WebElement::isDisplayed)
                && xButtonsOfSelectedTeachers.stream()
                .allMatch(WebElement::isDisplayed);
    }

    public void clickOnXButtonOfSelectedTeacher() {
        uiHelper.clickOnWebElement(xButtonsOfSelectedTeachers.get(xButtonsOfSelectedTeachers.size() - 1));
    }

    public void clickOnXButtonOfTheTeacherList() {
        uiHelper.clickOnWebElement(xButtonOfTheTeacherSelectBox);
    }

    public boolean checkThereAreNoSelectedItems() {
        return !selectTeacherBox.getAttribute("class").contains("clearable");
    }

    public String getTheSearchLinePlaceholder() {
        return teacherSelectField.getDomProperty("placeholder");
    }

    public boolean checkUIofAddTeachersPopup() {
        logger.info("Check UI of add teacher popup - teachers select field, xButton and Save button");
        return uiHelper.checkElementsAreDisplayed(
                        teacherSelectField,
                        xButton,
                        saveButton
                );
    }

    public boolean checkErrorMessagesOfBlankSelection() {
        logger.info("Check error messages of blank input fields");
        return errorMessageForSubjectsPopup
                .getText()
                .equals(ErrorMessagesProvider.getSelectionErrMessage());
    }

    public void saveQuantityOfMatchedItemsResult() {
        logger.info("Save {} quantity of matched items in the search result to Shared test data",
                teachersInDropDownList.size());
        SharedTestData.setResultOfMatchedSearch(teachersInDropDownList.size());
    }

    public void clearTeacherSelectField() {
        logger.info("Clear text in teacher select field.");
        teacherSelectField.clear();
    }

    public boolean checkResultQuantityIsTheSameAsWasInThePreviousSearch() {
        logger.info("Check result quantity {} is the same as was in the previous search {}",
                teachersInDropDownList.size(), SharedTestData.getResultOfMatchedSearch());
        return teachersInDropDownList.size() == SharedTestData.getResultOfMatchedSearch();
    }

    public int getCountOfSelectedTeachers() {
        logger.info("Get count of selected teachers in the box.");
        return SharedTestData.getSelectedTeachersCountInTheBox();
    }

    public void saveCountOfSelectedTeachersInTheBox() {
        logger.info("Save count of selected teachers in the box.");
        SharedTestData.setSelectedTeachersCountInTheBox(listOfSelectedTeachers.size());
    }
}
