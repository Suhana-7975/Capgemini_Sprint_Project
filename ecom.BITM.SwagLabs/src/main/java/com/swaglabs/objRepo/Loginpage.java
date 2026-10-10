package com.swaglabs.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Loginpage {

    private WebDriver driver;

    // Username
    private By username = By.id("user-name");

    // Password
    private By password = By.id("password");

    // Login button
    private By loginButton = By.id("login-button");

    // Error message
    private By errorMessage = By.cssSelector("h3[data-test='error']");


    public Loginpage(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getUsername() {
        return driver.findElement(username);
    }


    public WebElement getPassword() {
        return driver.findElement(password);
    }


    public WebElement getLoginButton() {
        return driver.findElement(loginButton);
    }


    public WebElement getErrorMessage() {
        return driver.findElement(errorMessage);
    }


    public void enterUsername(String usernameValue) {
        getUsername().sendKeys(usernameValue);
    }


    public void enterPassword(String passwordValue) {
        getPassword().sendKeys(passwordValue);
    }


    public void clickLogin() {
        getLoginButton().click();
    }
}

