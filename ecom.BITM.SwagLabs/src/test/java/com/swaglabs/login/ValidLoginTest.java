package com.swaglabs.login;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.baseClass.BaseClass;
import com.swaglabs.objRepo.Loginpage;
import com.swaglabs.utility.ExcelUtility;

public class ValidLoginTest extends BaseClass {

    @Test(priority = 1, groups = {"ST", "FT", "IT", "YT" })
    public void verifyLoginpageAndValidLogin() throws IOException {

        Loginpage loginPage = new Loginpage(driver);

        // Verify login page elements
        Assert.assertTrue(loginPage.getUsername().isDisplayed(),
                "Username field is not displayed");

        Assert.assertTrue(loginPage.getPassword().isDisplayed(),
                "Password field is not displayed");

        Assert.assertTrue(loginPage.getLoginButton().isDisplayed(),
                "Login button is not displayed");

        // Row 1 = valid user (Excel row 2). Row 0 is the header.
        String username = ExcelUtility.getCellData("LoginData", 1, 0);
        String password = ExcelUtility.getCellData("LoginData", 1, 1);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Verify successful login using the URL (the page title is "Swag Labs" even on the login page)
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html",
                "Login was not successful");
    }
}