package com.swaglabs.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage2 {

    private WebDriver driver;

    // Checkout Overview page title
    private By overviewTitle = By.className("title");

    // Product name
    private By productName = By.className("inventory_item_name");

    // Payment Information
    private By paymentInfo = By.xpath("//div[contains(text(),'Payment Information')]");

    // Shipping Information
    private By shippingInfo = By.xpath("//div[contains(text(),'Shipping Information')]");

    // Item Total
    private By itemTotal = By.className("summary_subtotal_label");

    // Tax
    private By tax = By.className("summary_tax_label");

    // Total
    private By total = By.className("summary_total_label");

    // Finish button
    private By finishButton = By.id("finish");

    // Cancel button
    private By cancelButton = By.id("cancel");


    public CheckoutPage2(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getOverviewTitle() {
        return driver.findElement(overviewTitle);
    }


    public WebElement getProductName() {
        return driver.findElement(productName);
    }


    public WebElement getPaymentInfo() {
        return driver.findElement(paymentInfo);
    }


    public WebElement getShippingInfo() {
        return driver.findElement(shippingInfo);
    }


    public WebElement getItemTotal() {
        return driver.findElement(itemTotal);
    }


    public WebElement getTax() {
        return driver.findElement(tax);
    }


    public WebElement getTotal() {
        return driver.findElement(total);
    }


    public WebElement getFinishButton() {
        return driver.findElement(finishButton);
    }


    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }


    public void clickFinish() {
        getFinishButton().click();
    }


    public void clickCancel() {
        getCancelButton().click();
    }
}


