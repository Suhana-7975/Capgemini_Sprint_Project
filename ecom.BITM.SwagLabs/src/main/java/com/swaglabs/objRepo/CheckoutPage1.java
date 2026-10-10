package com.swaglabs.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage1 {

    private WebDriver driver;

    // Checkout page title
    private By checkoutTitle = By.className("title");

    // First Name
    private By firstName = By.id("first-name");

    // Last Name
    private By lastName = By.id("last-name");

    // Postal Code
    private By postalCode = By.id("postal-code");

    // Continue button
    private By continueButton = By.id("continue");

    // Cancel button
    private By cancelButton = By.id("cancel");

    // Error message (NEW)
    private By errorMessage = By.cssSelector("h3[data-test='error']");


    public CheckoutPage1(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getCheckoutTitle() {
        return driver.findElement(checkoutTitle);
    }


    public WebElement getFirstName() {
        return driver.findElement(firstName);
    }


    public WebElement getLastName() {
        return driver.findElement(lastName);
    }


    public WebElement getPostalCode() {
        return driver.findElement(postalCode);
    }


    public WebElement getContinueButton() {
        return driver.findElement(continueButton);
    }


    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }


    // NEW
    public WebElement getErrorMessage() {
        return driver.findElement(errorMessage);
    }


    public void enterFirstName(String firstNameValue) {
        getFirstName().sendKeys(firstNameValue);
    }


    public void enterLastName(String lastNameValue) {
        getLastName().sendKeys(lastNameValue);
    }


    public void enterPostalCode(String postalCodeValue) {
        getPostalCode().sendKeys(postalCodeValue);
    }


    public void clickContinue() {
        getContinueButton().click();
    }


    public void clickCancel() {
        getCancelButton().click();
    }
}