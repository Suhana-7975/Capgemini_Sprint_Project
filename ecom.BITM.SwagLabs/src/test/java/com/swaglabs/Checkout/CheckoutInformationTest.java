package com.swaglabs.Checkout;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.baseClass.BaseClass;
import com.swaglabs.objRepo.CartPage;
import com.swaglabs.objRepo.CheckoutPage1;
import com.swaglabs.objRepo.Loginpage;
import com.swaglabs.objRepo.ProductPage;
import com.swaglabs.utility.ExcelUtility;

public class CheckoutInformationTest extends BaseClass {

    @Test(priority = 1, groups = {"ST", "FT", "IT", "YT", })
    public void verifyCheckoutInformationPage() throws IOException {

        Loginpage loginPage = new Loginpage(driver);

        // Read login data from Excel (row 1 = Excel row 2)
        String username = ExcelUtility.getCellData("LoginData", 1, 0).trim();
        String password = ExcelUtility.getCellData("LoginData", 1, 1).trim();

        

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Stops here with a clear message if login failed
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html",
                "Login failed with username [" + username + "] and password [" + password + "]");

        ProductPage productPage = new ProductPage(driver);
        productPage.clickAddToCart();
        productPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html",
                "User did not navigate to Checkout Information page");

        CheckoutPage1 checkoutPage1 = new CheckoutPage1(driver);

        Assert.assertTrue(checkoutPage1.getCheckoutTitle().isDisplayed(),
                "Checkout page is not displayed");

        Assert.assertEquals(checkoutPage1.getCheckoutTitle().getText(),
                "Checkout: Your Information",
                "Incorrect checkout page");

        String firstName = ExcelUtility.getCellData("CheckoutData", 1, 0).trim();
        String lastName = ExcelUtility.getCellData("CheckoutData", 1, 1).trim();
        String postalCode = ExcelUtility.getCellData("CheckoutData", 1, 2).trim();

        checkoutPage1.enterFirstName(firstName);
        checkoutPage1.enterLastName(lastName);
        checkoutPage1.enterPostalCode(postalCode);

        checkoutPage1.clickContinue();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html",
                "User did not navigate to Checkout Overview");
    }
}