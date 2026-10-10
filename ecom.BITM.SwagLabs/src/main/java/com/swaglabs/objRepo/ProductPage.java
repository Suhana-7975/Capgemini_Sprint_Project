package com.swaglabs.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {

    private WebDriver driver;

    // Product title
    private By productsTitle = By.className("title");

    // Add to Cart button for first product
    private By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");

    // Cart icon
    private By cartIcon = By.className("shopping_cart_link");

    // Cart item count
    private By cartCount = By.className("shopping_cart_badge");

    // Product sorting dropdown
    private By sortDropdown = By.className("product_sort_container");


    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getProductsTitle() {
        return driver.findElement(productsTitle);
    }


    public WebElement getAddToCartButton() {
        return driver.findElement(addToCartButton);
    }


    public WebElement getCartIcon() {
        return driver.findElement(cartIcon);
    }


    public WebElement getCartCount() {
        return driver.findElement(cartCount);
    }


    public WebElement getSortDropdown() {
        return driver.findElement(sortDropdown);
    }


    public void clickAddToCart() {
        getAddToCartButton().click();
    }


    public void clickCartIcon() {
        getCartIcon().click();
    }
}