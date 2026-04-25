package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.ElementsActions;
import org.example.utils.LogsUtil;
import org.example.utils.Validations;
import org.example.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }
    private boolean hasVariants = false;

    //locators
    By dropDown = By.id("quantity");
    By secondVariant = By.xpath("(//li[contains(@class,'swatch-list-item-text')]//input[@class='a-button-input'])[2]");
    By addToCart = By.id("add-to-cart-button");
    By clickNoThanks = By.cssSelector("input[aria-labelledby='attachSiNoCoverage-announce']");
    By message = By.cssSelector("h1.sw-atc-text");
    By productName = By.id("productTitle");
    By sizeLabel = By.xpath("//span[normalize-space()='Size:']");
    By color = By.xpath("//span[normalize-space()='White']");
    By price = By.xpath("//span[@class='a-price-whole']");
    By fraction = By.xpath("//span[@class='a-price-fraction']");


    //actions
    @Step("Select quantity of the product")
    public ProductPage selectQuantity(int qty) {
        try {
            Select select = new Select(ElementsActions.findElement(driver, dropDown));
            select.selectByValue(String.valueOf(qty));
            LogsUtil.info("Quantity selected: " + qty);
        } catch (Exception e) {
            LogsUtil.info("Quantity dropdown not available, proceeding with default quantity");
        }
        return this;
    }

    @Step("Click Add to Cart button")
    public ProductPage clickOnAddToCartButton() {
        Waits.waitUntilClickable(driver, addToCart);
        ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('add-to-cart-button').click();"
        );
        return this;
    }

    @Step("Select the second variant of the product if available")
    public ProductPage clickOnSecondVariant() {
        try {
            ElementsActions.findElement(driver, secondVariant);
            ElementsActions.clickElement(driver, secondVariant);
            LogsUtil.info("Second variant selected");
            hasVariants = true;
        } catch (Exception e) {
            LogsUtil.info("Second variant not available, proceeding with default selection");
        }
        return this;
    }

    @Step("Dismiss the protection plan pop-up if it appears")
    public ProductPage clickNoThanks() {
        try {
            ElementsActions.findElement(driver, clickNoThanks);
            ElementsActions.clickElement(driver, clickNoThanks);
            LogsUtil.info("Protection plan popup dismissed");
        } catch (Exception e) {
            LogsUtil.info("No such pop-up appeared");
        }
        return this;
    }

    @Step("Get the message displayed after adding the product to the cart")
    public String getMessage() {
        return ElementsActions.getText(driver, message);
    }

    @Step("Get the expected product name")
    public String getExpectedProductName() {
        return ElementsActions.getText(driver, productName);
    }

    public String getExpectedSize() {
        if (!hasVariants) {
            LogsUtil.info("Skipping size lookup — no variant swatch was found on this product");
            return "";
        }
        try {
            LogsUtil.info("Attempting to retrieve size information for the product");
            return " " + ElementsActions.getText(driver, sizeLabel);
        } catch (Exception e) {
            LogsUtil.info("Size information not available for this product");
            return "";
        }

    }

    public String getExpectedValue() {
        if (!hasVariants) {
            LogsUtil.info("Skipping color lookup — no variant swatch was found on this product");
            return "";
        }
        try {
            LogsUtil.info("Attempting to retrieve color information for the product");
            return " " + ElementsActions.getText(driver, color);
        } catch (Exception e) {
            LogsUtil.info("Color information not available for this product");
            return "";
        }
    }

    public String getFullProductName() {
        return getExpectedProductName() + getExpectedSize() + getExpectedValue();
    }

    @Step("Get the expected product price")
    public String getExpectedPriceWhole() {
        return ElementsActions.getText(driver, price);
    }
    public String getProductFraction() {
        return ElementsActions.getText(driver, fraction);
    }

    public String getExpectedProductPrice () {
        return getExpectedPriceWhole()+ "." + getProductFraction();
    }

    //assertions
    @Step("Validate that the product was added to the cart successfully")
    public ProductPage validateProductAddedToCart() {
        Validations.validateEqual(getMessage(),"Added to cart" ,"Could not find the message" );
        return this;
    }
    @Step("Validate that the product details are correct name: {expectedName}, price: {expectedPrice}")
    public ProductPage validateProductDetails(String expectedName, String expectedPrice) {
        String actualName = getFullProductName();
        String actualPrice = getExpectedProductPrice();
        Validations.validateEqual(actualName, expectedName, "Product name does not match");
        Validations.validateEqual(actualPrice, expectedPrice, "Product price does not match");
        return this;
    }
}
