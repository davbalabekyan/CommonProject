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

    public boolean popupIsClosed() {
        return adminsSection.isDisplayed();
    }
}
