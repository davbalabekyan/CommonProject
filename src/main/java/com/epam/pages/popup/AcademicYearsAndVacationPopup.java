package com.epam.pages.popup;

import com.epam.helpers.ErrorMessagesProvider;
import com.epam.helpers.SharedTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AcademicYearsAndVacationPopup extends CreatePopup {
    @FindBy(id = "startDate")
    private WebElement startDate;
    @FindBy(id = "endDate")
    private WebElement endDate;
    @FindBy(className = "ui-datepicker-month")
    private WebElement monthToSelect;
    @FindBy(className = "ui-datepicker-year")
    private WebElement yearToSelect;
    @FindBy(xpath = "//td[contains(@class, 'disabled') and not(contains(@class, 'other-month'))]")
    private List<WebElement> disabledDates;
    @FindBy(xpath = "//div[@class='error']/p")
    private WebElement errorMessageForWrongSelectedDates;
    @FindBy(xpath = "//select[@class='ui-datepicker-year']/option")
    private List<WebElement> listOfYears;

    public boolean checkUIOfCreatePopupYearsSection() {
        logger.info("Check UI of create popup in years section");
        return uiHelper.checkElementsAreDisplayed(
                title,
                startDate,
                endDate,
                xButton,
                saveButton
        );
    }

    public boolean areDatesSelected() {
        logger.info("Check if start date and end date are selected");
        return uiHelper.areElementsSelected(
                startDate,
                endDate
        );
    }

    public boolean checkErrorMessageForWrongSelectedDates() {
        logger.info("Check if dates are selected wrong - start date < end date.");
        return errorMessageForWrongSelectedDates.getText()
                .equals(ErrorMessagesProvider.getWrongSelectedDatesErrMessage());
    }

    public boolean checkErrorMessageForForFillingLessThan30Days() {
        logger.info("Check if dates are selected wrong - end date - start date < 30.");
        return errorMessageForWrongSelectedDates.getText()
                .equals(ErrorMessagesProvider.getLessThan30DaysErrMessage());
    }

    public void selectDate(int day, String month, int year, String field) {
        logger.info("Fill {} date - {}/{}/{}", field, day, month, year);
        if (field.contains("start")) {
            uiHelper.clickOnWebElement(startDate);
        } else if (field.contains("end")) {
            uiHelper.clickOnWebElement(endDate);
        }
        new Select(yearToSelect).selectByValue(String.valueOf(year));
        new Select(monthToSelect).selectByVisibleText(month);
        selectDay(day).click();
    }

    public boolean isDateBeforeTodayEnabled() {
        logger.info("Check if dates before today are enabled for selection.");
        int day = LocalDate.now().getDayOfMonth();
        uiHelper.clickOnWebElement(startDate);
        return disabledDates.stream().allMatch(dates -> (Integer.parseInt(dates.getText()) < day)
                && dates.getAttribute("class").contains("disabled"));
    }

    private WebElement selectDay(int day) {
        return driver.findElement(By.xpath(String.format("//a[@data-date='%s']", day)));
    }

    public boolean isYearGraterThan10Years(int year) {
        logger.info("Check if filled year is grater than 10 years from the moment of selection");
        int startDateValue = SharedTestData.getStartDate().getYear();
        return (year - startDateValue) > 10;
    }

    public boolean checkIfYearIsPresentInTheSelectList(int year) {
        uiHelper.clickOnWebElement(endDate);
        for (WebElement years : listOfYears) {
            if (years.getText().equals(String.valueOf(year))) {
                return true;
            }
        }
        return false;
    }

    public void saveStartDateValue() {
        logger.info("Save start date value");
        SharedTestData.setStartDate(LocalDate.parse(startDate.getDomProperty("value"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    public void saveEndDateValue() {
        logger.info("Save end date value");
        SharedTestData.setEndDate(LocalDate.parse(endDate.getDomProperty("value"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
}