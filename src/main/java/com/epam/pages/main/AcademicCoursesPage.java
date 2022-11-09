package com.epam.pages.main;

import com.epam.pages.common.CommonPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AcademicCoursesPage extends CommonPage {

    @FindBy(xpath = "//div[@class='sidebar2']/a[@href='/subjects/Languages/teachers']")
    private WebElement teachersSection;

}
