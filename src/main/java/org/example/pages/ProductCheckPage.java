package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.ElementsActions;
import org.example.utils.LogsUtil;
import org.example.utils.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductCheckPage extends BasePage{

    public ProductCheckPage(WebDriver driver) {
        super(driver);
    }

    //locators
    By subTotalSymbol = By.cssSelector("span.a-price-symbol");
    By subTotalWhole = By.cssSelector("span.a-price-whole");
    By subTotalFraction = By.cssSelector("span.a-price-fraction");
    By goToBasket = By.cssSelector("a.a-button-text");
    By addedToCart = By.cssSelector("h1.sw-atc-text");

    //actions

    public String getExpectedProductSubTotalSymbol() {
        return ElementsActions.getText(driver, subTotalSymbol);
    }
    public String getExpectedProductSubTotalWhole() {
        return ElementsActions.getText(driver, subTotalWhole);
    }
    public String getExpectedProductSubTotalFraction() {
        return ElementsActions.getText(driver, subTotalFraction);
    }

    @Step("Get the expected product subtotal")
    public String getExpectedSubTotal() {
        return  "  "+getExpectedProductSubTotalWhole()+ "." + getExpectedProductSubTotalFraction();
    }


    @Step("Click on 'Go to Basket' button to proceed to the cart page")
    public ProductCheckPage clickOnGoToBasket() {
        ElementsActions.clickElement(driver, goToBasket);
        return this;
    }


    //assertions
    @Step("Validate that the product was added to the cart successfully")
    public ProductCheckPage validateProductAddedToCart() {
        String expectedMessage = "Added to cart";
        String actualMessage = ElementsActions.getText(driver,addedToCart);
        Validations.validateEqual(actualMessage, expectedMessage, "Product was not added to cart successfully");
        return this;
    }
    @Step("Validate that the product subtotal matches the expected price")
    public ProductCheckPage validateSubtotal(String expectedPrice) {
        String actualSubTotal = getExpectedSubTotal();
        Validations.validateEqual(actualSubTotal, expectedPrice, "Product subtotal does not match expected price");
        return this;
    }



}
