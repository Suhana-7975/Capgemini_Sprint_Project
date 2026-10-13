package com.swaglabs.Checkout;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.baseClass.BaseClass;
import com.swaglabs.objRepo.CartPage;
import com.swaglabs.objRepo.CheckoutPage1;
import com.swaglabs.objRepo.CheckoutPage2;
import com.swaglabs.objRepo.Loginpage;
import com.swaglabs.objRepo.ProductPage;

public class OrderCompletionTest extends BaseClass {

    @Test(priority = 1, groups = { "FT", "IT" })
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("checkout-step-two"));

        CheckoutPage2 checkoutPage2 = new CheckoutPage2(driver);

        Assert.assertTrue(
                checkoutPage2.getOverviewTitle().isDisplayed(),
                "Checkout Overview page is not displayed"
        );

        checkoutPage2.clickFinish();

        wait.until(ExpectedConditions.urlContains("checkout-complete"));

        WebElement completeTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='title']")));

        Assert.assertTrue(
                completeTitle.isDisplayed(),
                "Checkout Complete page is not displayed"
        );

        Assert.assertEquals(
                completeTitle.getText(),
                "Checkout: Complete!",
                "Incorrect completion page"
        );

        WebElement confirmation = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='complete-header']")));

        Assert.assertTrue(
                confirmation.isDisplayed(),
                "Confirmation message is not displayed"
        );

        Assert.assertEquals(
                confirmation.getText(),
                "Thank you for your order!",
                "Order confirmation message is incorrect"
        );
    }
}