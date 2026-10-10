package com.swaglabs.login;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.baseClass.BaseClass;
import com.swaglabs.objRepo.Loginpage;
import com.swaglabs.utility.ExcelUtility;

public class InvalidLoginTest extends BaseClass {

    @Test(priority = 1, groups = { "FT", "NT"})
    public void verifyInvalidLogin() throws IOException {

        Loginpage loginPage = new Loginpage(driver);

        String username = ExcelUtility.getCellData("LoginData", 2, 0);
        String password = ExcelUtility.getCellData("LoginData", 2, 1);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed(),
                "Error message is not displayed");

        String actualMessage = loginPage.getErrorMessage().getText();

        Assert.assertTrue(
                actualMessage.contains("Username and password do not match"),
                "Incorrect error message displayed");
    }
}
