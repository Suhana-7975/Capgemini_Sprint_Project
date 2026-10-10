package com.swaglabs.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    private WebDriver driver;

    // Cart page title
    private By cartTitle = By.className("title");

    // First product in cart
    private By cartItem = By.className("inventory_item_name");

    // Remove button for Sauce Labs Backpack
    private By removeButton = By.id("remove-sauce-labs-backpack");

    // Checkout button
    private By checkoutButton = By.id("checkout");

    // Continue Shopping button
    private By continueShoppingButton = By.id("continue-shopping");


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getCartTitle() {
        return driver.findElement(cartTitle);
    }


    public WebElement getCartItem() {
        return driver.findElement(cartItem);
    }


    public WebElement getRemoveButton() {
        return driver.findElement(removeButton);
    }


    public WebElement getCheckoutButton() {
        return driver.findElement(checkoutButton);
    }


    public WebElement getContinueShoppingButton() {
        return driver.findElement(continueShoppingButton);
    }


    public void removeProduct() {
        getRemoveButton().click();
    }


    public void clickCheckout() {
        getCheckoutButton().click();
    }


    public void clickContinueShopping() {
        getContinueShoppingButton().click();
    }
}
