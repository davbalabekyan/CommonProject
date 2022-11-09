package com.epam.pages.popup;

import com.epam.helpers.ErrorMessagesProvider;
import com.epam.helpers.SharedTestData;
import com.epam.helpers.UserDataProvider;
import com.epam.pages.common.CommonPopup;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AcademicClassPopup extends CommonPopup {
    @FindBy(id = "classNumber")
    private WebElement classNumber;
    @FindBy(xpath = "//input/following-sibling::div[@class='error']")
    protected WebElement errorMessagesOfExistedClass;

    public boolean checkUIOfCreatePopupClassesSection() {
        logger.info("Check UI of create popup in academic classes section");
        return uiHelper.checkElementsAreDisplayed(
                classNumber,
                xButton,
                saveButton,
                title
        );
    }

    public void fillExistedAcademicClass() {
        logger.info("Fill existed academic class from list.");
            uiHelper.sendKeys(classNumber, UserDataProvider.getExistedClass());
    }

    public boolean checkExistedClassErrorMessage() {
        logger.info("Get error message of existed class");
        return errorMessagesOfExistedClass.getText()
                .equals(ErrorMessagesProvider.getExistedAcademicClassErrMessage());
    }

    public void fillAcademicClass() {
        String generateClass = RandomStringUtils.random(25,true,true);
        logger.info("Fill academic class with value - {}", generateClass);
        uiHelper.sendKeys(classNumber, generateClass);
    }

    public void saveAcademicClassValue() {
        SharedTestData.setAcademicClass(classNumber.getDomProperty("value"));
    }
}
