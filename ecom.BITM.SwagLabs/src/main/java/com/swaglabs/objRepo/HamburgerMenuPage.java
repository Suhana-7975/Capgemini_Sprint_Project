package com.swaglabs.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HamburgerMenuPage {

    private WebDriver driver;

    // Hamburger menu button
    private By menuButton = By.id("react-burger-menu-btn");

    // All Items
    private By allItems = By.id("inventory_sidebar_link");

    // About
    private By about = By.id("about_sidebar_link");

    // Logout
    private By logout = By.id("logout_sidebar_link");

    // Reset App State
    private By resetAppState = By.id("reset_sidebar_link");


    public HamburgerMenuPage(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getMenuButton() {
        return driver.findElement(menuButton);
    }


    public WebElement getAllItems() {
        return driver.findElement(allItems);
    }


    public WebElement getAbout() {
        return driver.findElement(about);
    }


    public WebElement getLogout() {
        return driver.findElement(logout);
    }


    public WebElement getResetAppState() {
        return driver.findElement(resetAppState);
    }


    public void clickMenuButton() {
        getMenuButton().click();
    }


    public void clickAllItems() {
        getAllItems().click();
    }


    public void clickAbout() {
        getAbout().click();
    }


    public void clickLogout() {
        getLogout().click();
    }


    public void clickResetAppState() {
        getResetAppState().click();
    }
}
