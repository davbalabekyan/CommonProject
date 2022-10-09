package com.epam.pages.main;

import com.epam.helpers.SharedTestData;
import com.epam.jdbc.service.AdminService;
import com.epam.pages.common.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AdminPage extends CommonPage {

   private final AdminService adminService = new AdminService();
    public boolean checkAllElementsArePresent() {
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

    private WebElement getSectionElementByName(String section) {
        return driver.findElement(By.xpath(String.format("//a[@href='/%s']", section)));
    }

    public boolean checkNewTeacherIsDisplayedOnAdminsSection() {
        return getNameOfLastCreatedUser()
                .equals(SharedTestData.getNameField())
                && getSurnameOfLastCreatedUser()
                .equals(SharedTestData.getSurnameField());
    }

    public boolean checkAdminIsNotAddedInTheDB() {
        return adminService.findByEmail(SharedTestData.getLastGeneratedEmail()).getEmail() == null;
    }

    public void selectSection(String section) {
        uiHelper.clickOnWebElement(getSectionElementByName(section));
    }
}
