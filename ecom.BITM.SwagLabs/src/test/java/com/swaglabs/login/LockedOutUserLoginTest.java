package com.swaglabs.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.baseClass.BaseClass;
import com.swaglabs.objRepo.Loginpage;

public class LockedOutUserLoginTest extends BaseClass {

    @Test(priority = 2, groups = { "FT", "NT"})
    public void verifyLockedOutUserLogin() {

        Loginpage loginPage = new Loginpage(driver);

        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed(),
                "Error message is not displayed");

        String actualMessage = loginPage.getErrorMessage().getText();

        Assert.assertTrue(
                actualMessage.contains("this user has been locked out"),
                "Incorrect error message displayed: " + actualMessage);

        // The user must stay on the login page
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/",
                "Locked-out user should not reach the inventory page");
    }
}