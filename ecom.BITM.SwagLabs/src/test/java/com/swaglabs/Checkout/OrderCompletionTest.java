package com.swaglabs.Checkout;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.baseClass.BaseClass;
import com.swaglabs.objRepo.CartPage;
import com.swaglabs.objRepo.CheckoutPage1;
import com.swaglabs.objRepo.CheckoutPage2;
import com.swaglabs.objRepo.CheckoutPage3;
import com.swaglabs.objRepo.Loginpage;
import com.swaglabs.objRepo.ProductPage;

public class OrderCompletionTest extends BaseClass {

    @Test(priority = 1, groups = { "FT", "IT"})
    public void verifyOrderCompletion() {

        Loginpage loginPage = new Loginpage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        ProductPage productPage = new ProductPage(driver);

        productPage.clickAddToCart();
        productPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

        CheckoutPage1 checkoutPage1 = new CheckoutPage1(driver);

        checkoutPage1.enterFirstName("Suhana");
        checkoutPage1.enterLastName("Test");
        checkoutPage1.enterPostalCode("583101");
        checkoutPage1.clickContinue();

        CheckoutPage2 checkoutPage2 = new CheckoutPage2(driver);

        Assert.assertTrue(
                checkoutPage2.getOverviewTitle().isDisplayed(),
                "Checkout Overview page is not displayed"
        );

        checkoutPage2.clickFinish();

        CheckoutPage3 checkoutPage3 = new CheckoutPage3(driver);

        Assert.assertTrue(
                checkoutPage3.getCompleteTitle().isDisplayed(),
                "Checkout Complete page is not displayed"
        );

        Assert.assertEquals(
                checkoutPage3.getCompleteTitle().getText(),
                "Checkout: Complete!",
                "Incorrect completion page"
        );

        Assert.assertTrue(
                checkoutPage3.getConfirmationMessage().isDisplayed(),
                "Confirmation message is not displayed"
        );

        Assert.assertEquals(
                checkoutPage3.getConfirmationMessage().getText(),
                "Thank you for your order!",
                "Order confirmation message is incorrect"
        );
    }
}
