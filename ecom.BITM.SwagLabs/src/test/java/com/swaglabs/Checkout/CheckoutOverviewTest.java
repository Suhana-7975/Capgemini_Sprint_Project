package com.swaglabs.Checkout;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.baseClass.BaseClass;
import com.swaglabs.objRepo.CartPage;
import com.swaglabs.objRepo.CheckoutPage1;
import com.swaglabs.objRepo.CheckoutPage2;
import com.swaglabs.objRepo.Loginpage;
import com.swaglabs.objRepo.ProductPage;

public class CheckoutOverviewTest extends BaseClass {

    @Test(priority = 1, groups = { "FT", "IT"})
    public void verifyCheckoutOverview() {

        // Login
        Loginpage loginPage = new Loginpage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Product Page
        ProductPage productPage = new ProductPage(driver);

        productPage.clickAddToCart();
        productPage.clickCartIcon();

        // Cart Page
        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

        // Checkout Page 1
        CheckoutPage1 checkoutPage1 = new CheckoutPage1(driver);

        checkoutPage1.enterFirstName("Suhana");
        checkoutPage1.enterLastName("Test");
        checkoutPage1.enterPostalCode("583101");
        checkoutPage1.clickContinue();

        // Checkout Page 2
        CheckoutPage2 checkoutPage2 = new CheckoutPage2(driver);

        // Verify Overview page
        Assert.assertTrue(checkoutPage2.getOverviewTitle().isDisplayed(),
                "Checkout Overview page is not displayed");

        Assert.assertEquals(checkoutPage2.getOverviewTitle().getText(),
                "Checkout: Overview",
                "Incorrect Checkout Overview page");

        // Verify product is displayed
        Assert.assertTrue(checkoutPage2.getProductName().isDisplayed(),
                "Product is not displayed in Overview");

        // Verify payment information
        Assert.assertTrue(checkoutPage2.getPaymentInfo().isDisplayed(),
                "Payment information is not displayed");

        // Verify shipping information
        Assert.assertTrue(checkoutPage2.getShippingInfo().isDisplayed(),
                "Shipping information is not displayed");

        // Verify total
        Assert.assertTrue(checkoutPage2.getTotal().isDisplayed(),
                "Total amount is not displayed");
    }
}

