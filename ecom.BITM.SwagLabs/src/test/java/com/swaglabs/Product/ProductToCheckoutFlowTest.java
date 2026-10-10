package com.swaglabs.Product;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.baseClass.BaseClass;
import com.swaglabs.objRepo.CartPage;
import com.swaglabs.objRepo.CheckoutPage1;
import com.swaglabs.objRepo.CheckoutPage2;
import com.swaglabs.objRepo.Loginpage;
import com.swaglabs.objRepo.ProductPage;

public class ProductToCheckoutFlowTest extends BaseClass {

    @Test(priority = 1, groups = {"ST", "FT", "IT", "YT"})
    public void verifyCheckoutOverview() {

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

        Assert.assertEquals(
                checkoutPage2.getOverviewTitle().getText(),
                "Checkout: Overview",
                "Incorrect Checkout Overview page"
        );

        Assert.assertTrue(
                checkoutPage2.getProductName().isDisplayed(),
                "Product is not displayed in Overview"
        );

        Assert.assertTrue(
                checkoutPage2.getPaymentInfo().isDisplayed(),
                "Payment information is not displayed"
        );

        Assert.assertTrue(
                checkoutPage2.getShippingInfo().isDisplayed(),
                "Shipping information is not displayed"
        );

        Assert.assertTrue(
                checkoutPage2.getTotal().isDisplayed(),
                "Total amount is not displayed"
        );
    }
}
