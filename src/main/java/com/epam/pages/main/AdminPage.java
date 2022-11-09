package com.epam.pages.main;

import com.epam.helpers.SharedTestData;
import com.epam.pages.common.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AdminPage extends CommonPage {

    public boolean checkAllElementsArePresent() {
        logger.info("Check elements: list, role name, name and surname," +
                " sections, settings and create button are present");
        return uiHelper.checkElementsAreDisplayed(
                list,
                roleName,
                nameAndSurname,
                getSectionElementByName("teachers"),
                getSectionElementByName("students"),
                getSectionElementByName("parents"),
                getSectionElementByName("classes"),
                getSectionElementByName("years"),
                getSectionElementByName("subjects"),
                getSectionElementByName("vacations"),
                settingsSection,
                createButton
        );
    }

    public boolean checkUIOfSection() {
        logger.info("Check elements are displayed on the given section - list, create button");
        return uiHelper.checkElementsAreDisplayed(
                list,
                createButton
        );
    }

    private WebElement getSectionElementByName(String section) {
        return driver.findElement(By.xpath(String.format("//a[@href='/%s']", section)));
    }

    public boolean checkNewUserIsDisplayedOnAdminsSection() {
        logger.info("Check new user is displayed");
        return getNameOfLastCreatedUser()
                .equals(SharedTestData.getNameField())
                && getSurnameOfLastCreatedUser()
                .equals(SharedTestData.getSurnameField());
    }
    public boolean checkAcademicClassIsDisplayedInTheList() {
        logger.info("Get last created academic class and check if it is added to the list");
        return (listItemsHref.get(listItemsHref.size() - 1).getText())
                .equals(SharedTestData.getAcademicClass());
    }
    public boolean checkNewCreatedItemIsDisplayedOnAdminsSection() {
        logger.info("Check new created item is displayed on admins section");
        return getNameOfLastCreatedItem().equals(SharedTestData.getLastCreatedItemName());
    }

    public void selectSection(String section) {
        uiHelper.clickOnWebElement(getSectionElementByName(section));
    }

    public boolean checkVacationIsDisplayedInTheList() {
        logger.info("Get last created vacation and check if it is added to the list");
        return (listItems.get(listItems.size() - 1).getText())
                .equals(getVacationAsDisplayedInTheList());
    }
    private String getVacationAsDisplayedInTheList() {
        return String.format("%d/%d/%s - %d/%d/%s",
                SharedTestData.getStartDate().getMonthValue(),
                SharedTestData.getStartDate().getDayOfMonth(),
                String.valueOf(SharedTestData.getStartDate().getYear()).substring(2),
                SharedTestData.getEndDate().getMonthValue(),
                SharedTestData.getEndDate().getDayOfMonth(),
                String.valueOf(SharedTestData.getEndDate().getYear()).substring(2));
    }

    public boolean checkAcademicYearIsDisplayedInTheList() {
        logger.info("Get last created academic year and check if it is added to the list");
        return (listItems.get(listItems.size() - 1).getText())
                .equals(SharedTestData.getStartDate().getYear() + "-"
                        + SharedTestData.getEndDate().getYear());
    }
}
