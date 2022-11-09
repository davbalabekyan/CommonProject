package com.epam.pages.common;

import com.epam.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class CommonPage extends BasePage {

    @FindBy(xpath = "//a[@href='#']")
    protected WebElement roleName;
    @FindBy(xpath = "//*[@class='topnav-right-side']//a")
    protected WebElement nameAndSurname;
    @FindBy(id = "show-btn")
    protected WebElement createButton;
    @FindBy(className = "list")
    protected WebElement list;
    @FindBy(xpath = "//div[@class='list-items']/p")
    protected List<WebElement> listItems;
    @FindBy(xpath = "//div[@class='list-items']/a")
    protected List<WebElement> listItemsHref;
    @FindBy(linkText = "Settings")
    protected WebElement settingsSection;

    public String getRoleName() {
        String name = roleName.getText();
        logger.info("Get role name - {}", name);
        return name;
    }

    public String getNameAndSurname() {
        String surnameAndName = nameAndSurname.getText();
        logger.info("Get name and surname - {}", surnameAndName);
        return surnameAndName;
    }

    public String getNameOfLastCreatedUser() {
        String nameOfLastCreatedUser = listItems.get(listItems.size() - 1).getText().split(" ")[0];
        logger.info("Get name of last created user - {}", nameOfLastCreatedUser);
        return nameOfLastCreatedUser;
    }

    public String getSurnameOfLastCreatedUser() {
        String surnameOfLastCreatedUser = listItems.get(listItems.size() - 1).getText().split(" ")[1];
        logger.info("Get surname of last created user - {}", surnameOfLastCreatedUser);
        return surnameOfLastCreatedUser;
    }

    public String getNameOfLastCreatedItem() {
        return listItemsHref.get(listItemsHref.size() - 1).getText();
    }

    public void clickOnCreateButton() {
        uiHelper.clickOnWebElement(createButton);
    }
}
