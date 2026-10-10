package com.swaglabs.Product;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.baseClass.BaseClass;
import com.swaglabs.objRepo.Loginpage;
import com.swaglabs.objRepo.ProductPage;

public class AddToCartTest extends BaseClass {

    @Test(priority = 1, groups = {"ST", "FT", "IT", "YT"})
    public void verifyProductsAndAddToCart() {

        Loginpage loginPage = new Loginpage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        ProductPage productPage = new ProductPage(driver);

        Assert.assertTrue(
                productPage.getProductsTitle().isDisplayed(),
                "Products title is not displayed"
        );

        Assert.assertTrue(
                productPage.getAddToCartButton().isDisplayed(),
                "Add to Cart button is not displayed"
        );

        productPage.clickAddToCart();

        Assert.assertTrue(
                productPage.getCartCount().isDisplayed(),
                "Cart count is not displayed"
        );

        Assert.assertEquals(
                productPage.getCartCount().getText(),
                "1",
                "Product was not added to cart"
        );
    }
}
