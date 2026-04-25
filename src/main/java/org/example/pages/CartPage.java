package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.CustomSoftAssertion;
import org.example.utils.ElementsActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    //locators
    By checkForItem = By.cssSelector("input[data-action='delete-active']");
    By checkName = By.cssSelector("div.sc-list-item span.a-truncate-cut");
    By checkPrice = By.cssSelector("span.sc-product-price");
    By checkQty = By.xpath("//span[@data-a-selector='inner-value']");
    By checkSubtotal = By.id("sc-subtotal-amount-activecart");
    By cartCount = By.id("nav-cart-count");

    //actions
    public String checkForItem() {
        return ElementsActions.getText(driver, checkForItem);
    }
    @Step("Check product name in the cart")
    public String checkProductName() {
        return ElementsActions.getText(driver, checkName);
    }
    @Step("Check product price in the cart")
    public String checkProductPrice() {
        return ElementsActions.getText(driver, checkPrice);
    }
    @Step("Check product quantity in the cart")
    public String checkQty() {
        return ElementsActions.getText(driver, checkQty);
    }
    @Step("Check product subtotal in the cart")
    public String checkSubtotal() {
        return ElementsActions.getText(driver, checkSubtotal);
    }

    public String removeEgpFromPrice() {
        return checkProductPrice().replace("EGP", "");
    }

    public String removeEgpFromSubTotal() {
        return checkSubtotal().replace("EGP", "");

    }
    public String getCartCount() {
        return ElementsActions.getText(driver, cartCount);
    }


    //assertions
    @Step("Validate product details in the cart - name: {productName}, price: {productPrice}, quantity: {productQty}, subtotal: {productSubtotal}")
    public CartPage validateProductDetails(String productName, String productPrice, String productQty, String productSubtotal) {
        String actualProductName = checkProductName();
        String actualProductQty = checkQty();
        String actualProductPrice = removeEgpFromPrice();
        String actualProductSubtotal = removeEgpFromSubTotal();

        CustomSoftAssertion.softAssert.assertEquals(actualProductName, productName, "Product name does not match");
        CustomSoftAssertion.softAssert.assertEquals(actualProductPrice, productPrice, "Product price does not match");
        CustomSoftAssertion.softAssert.assertEquals(actualProductQty, productQty, "Product quantity does not match");
        CustomSoftAssertion.softAssert.assertEquals(actualProductSubtotal, productSubtotal, "Product subtotal does not match");
        return this;
    }



}

