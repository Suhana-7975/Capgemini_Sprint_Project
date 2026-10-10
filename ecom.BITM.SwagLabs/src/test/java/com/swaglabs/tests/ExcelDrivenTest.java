package com.swaglabs.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.swaglabs.baseClass.BaseClass;
import com.swaglabs.objRepo.CartPage;
import com.swaglabs.objRepo.CheckoutPage1;
import com.swaglabs.objRepo.CheckoutPage2;
import com.swaglabs.objRepo.CheckoutPage3;
import com.swaglabs.objRepo.Loginpage;
import com.swaglabs.objRepo.ProductPage;
import com.swaglabs.utility.TestCaseDataProvider;

public class ExcelDrivenTest extends BaseClass {

    private static final String HOME_URL      = "https://www.saucedemo.com/";
    private static final String INVENTORY_URL = "https://www.saucedemo.com/inventory.html";
    private static final String CART_URL      = "https://www.saucedemo.com/cart.html";
    private static final String STEP_ONE_URL  = "https://www.saucedemo.com/checkout-step-one.html";
    private static final String STEP_TWO_URL  = "https://www.saucedemo.com/checkout-step-two.html";

    @Test(dataProvider = "testCases", dataProviderClass = TestCaseDataProvider.class)
    public void executeTestCase(String tcId, String scenario, String module,
            String type, String title, String precondition, String steps,
            String testData, String expected, String actual, String priority) {

        switch (tcId.trim()) {

        case "TC-F-001":   verifyValidLogin();            break;
        case "TC-F-002":   verifyValidCheckoutInfo();     break;
        case "TC-S-001":   verifyLoginControls();         break;
        case "TC-S-002":   verifyAddToCartSmoke();        break;
        case "TC-I-001":   verifyProductToCart();         break;
        case "TC-I-002":   verifyCartToOverview();        break;
        case "TC-SYS-001": verifyCompletePurchase();      break;
        case "TC-SYS-002": verifyAddRemoveContinue();     break;
        case "TC-N-001":   verifyInvalidLogin();          break;
        case "TC-N-002":   verifyEmptyCart();             break;
        case "TC-N-003":   verifyFirstNameBlank();        break;

        default:
            throw new SkipException("No automation written yet for " + tcId);
        }
    }

    // ================= FUNCTIONAL =================

    private void verifyValidLogin() {
        login();
        Assert.assertEquals(driver.getCurrentUrl(), INVENTORY_URL,
                "User did not reach the Products page");
        Assert.assertEquals(new ProductPage(driver).getProductsTitle().getText(),
                "Products", "Products title is incorrect");
    }

    private void verifyValidCheckoutInfo() {
        openCheckoutInformation();
        fillCheckoutInfo();
        new CheckoutPage1(driver).clickContinue();

        Assert.assertEquals(driver.getCurrentUrl(), STEP_TWO_URL,
                "Checkout Overview page did not open");
        Assert.assertEquals(new CheckoutPage2(driver).getOverviewTitle().getText(),
                "Checkout: Overview", "Overview title is incorrect");
    }

    // ================= SMOKE =================

    private void verifyLoginControls() {
        Loginpage loginPage = new Loginpage(driver);
        Assert.assertTrue(loginPage.getUsername().isDisplayed(), "Username field is not displayed");
        Assert.assertTrue(loginPage.getPassword().isDisplayed(), "Password field is not displayed");
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed(), "Login button is not displayed");
    }

    private void verifyAddToCartSmoke() {
        login();
        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.getProductsTitle().isDisplayed(),
                "Products title is not displayed");

        productPage.clickAddToCart();

        Assert.assertEquals(productPage.getCartCount().getText(), "1",
                "Cart badge does not show 1");
    }

    // ================= INTEGRATION =================

    private void verifyProductToCart() {
        login();
        ProductPage productPage = new ProductPage(driver);
        productPage.clickAddToCart();
        productPage.clickCartIcon();

        Assert.assertEquals(driver.getCurrentUrl(), CART_URL, "Cart page did not open");
        Assert.assertEquals(new CartPage(driver).getCartItem().getText(),
                "Sauce Labs Backpack", "Selected product is not listed in the cart");
    }

    private void verifyCartToOverview() {
        openCart();
        new CartPage(driver).clickCheckout();
        Assert.assertEquals(driver.getCurrentUrl(), STEP_ONE_URL,
                "Checkout Information page did not open");

        fillCheckoutInfo();
        new CheckoutPage1(driver).clickContinue();

        Assert.assertEquals(driver.getCurrentUrl(), STEP_TWO_URL,
                "Checkout Overview page did not open");
        Assert.assertEquals(new CheckoutPage2(driver).getProductName().getText(),
                "Sauce Labs Backpack", "Selected product is not shown on Overview");
    }

    // ================= SYSTEM =================

    private void verifyCompletePurchase() {
        openCart();
        new CartPage(driver).clickCheckout();
        fillCheckoutInfo();
        new CheckoutPage1(driver).clickContinue();

        new CheckoutPage2(driver).clickFinish();

        CheckoutPage3 complete = new CheckoutPage3(driver);
        Assert.assertEquals(complete.getCompleteTitle().getText(),
                "Checkout: Complete!", "Complete page title is incorrect");
        Assert.assertEquals(complete.getConfirmationMessage().getText(),
                "Thank you for your order!", "Order confirmation message is incorrect");
    }

    private void verifyAddRemoveContinue() {
        openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.removeProduct();

        Assert.assertEquals(countElements(By.className("inventory_item_name")), 0,
                "Product was not removed from the cart");

        cartPage.clickContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), INVENTORY_URL,
                "User did not return to the Products page");
    }

    // ================= NEGATIVE =================

    private void verifyInvalidLogin() {
        Loginpage loginPage = new Loginpage(driver);
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed(),
                "Error message is not displayed");
        Assert.assertTrue(
                loginPage.getErrorMessage().getText().contains("Username and password do not match"),
                "Incorrect error message: " + loginPage.getErrorMessage().getText());
        Assert.assertEquals(driver.getCurrentUrl(), HOME_URL,
                "User should stay on the Login page");
    }

    private void verifyEmptyCart() {
        login();
        Assert.assertEquals(countElements(By.className("shopping_cart_badge")), 0,
                "Cart badge should not be shown for an empty cart");

        new ProductPage(driver).clickCartIcon();

        Assert.assertEquals(driver.getCurrentUrl(), CART_URL, "Cart page did not open");
        Assert.assertEquals(countElements(By.className("inventory_item_name")), 0,
                "Empty cart should not list any product");
    }

    private void verifyFirstNameBlank() {
        openCheckoutInformation();

        CheckoutPage1 checkout = new CheckoutPage1(driver);
        checkout.enterLastName("Test");
        checkout.enterPostalCode("583101");
        checkout.clickContinue();

        Assert.assertTrue(checkout.getErrorMessage().isDisplayed(),
                "Error message is not displayed");
        Assert.assertTrue(checkout.getErrorMessage().getText().contains("First Name is required"),
                "Incorrect error message: " + checkout.getErrorMessage().getText());
        Assert.assertEquals(driver.getCurrentUrl(), STEP_ONE_URL,
                "User should stay on Checkout Information page");
    }

    // ================= HELPERS =================

    private void login() {
        Loginpage loginPage = new Loginpage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }

    private void openCart() {
        login();
        ProductPage productPage = new ProductPage(driver);
        productPage.clickAddToCart();
        productPage.clickCartIcon();
    }

    private void openCheckoutInformation() {
        openCart();
        new CartPage(driver).clickCheckout();
    }

    private void fillCheckoutInfo() {
        CheckoutPage1 checkout = new CheckoutPage1(driver);
        checkout.enterFirstName("Suhana");
        checkout.enterLastName("Test");
        checkout.enterPostalCode("583101");
    }

    // Counts elements without waiting 30 seconds when none are expected
    private int countElements(By locator) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        int count = driver.findElements(locator).size();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return count;
    }
}