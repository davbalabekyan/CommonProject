package com.epam.pages.main;

import com.epam.pages.common.CommonPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SuperAdminPage extends CommonPage {

    @FindBy(linkText = "Admins")
    protected WebElement adminsSection;

    public boolean checkAdminsSectionIsPresent() {
        return adminsSection.isDisplayed();
    }

    public boolean checkAllElementsArePresent() {
        return uiHelper.checkElementsAreDisplayed(list,
                roleName,
                adminsSection,
                settingsSection,
                createButton);
    }
}
