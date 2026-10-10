package com.swaglabs.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage3 {

    private WebDriver driver;

    // Checkout Complete page title
    private By completeTitle = By.className("title");

    // Confirmation message
    private By confirmationMessage = By.className("complete-header");

    // Order dispatch message
    private By dispatchMessage = By.className("complete-text");

    // Back Home button
    private By backHomeButton = By.id("back-to-products");


    public CheckoutPage3(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getCompleteTitle() {
        return driver.findElement(completeTitle);
    }


    public WebElement getConfirmationMessage() {
        return driver.findElement(confirmationMessage);
    }


    public WebElement getDispatchMessage() {
        return driver.findElement(dispatchMessage);
    }


    public WebElement getBackHomeButton() {
        return driver.findElement(backHomeButton);
    }


    public void clickBackHome() {
        getBackHomeButton().click();
    }
}

