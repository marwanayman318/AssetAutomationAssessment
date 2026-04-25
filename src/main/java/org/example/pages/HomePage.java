package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.BrowserActions;
import org.example.utils.ElementsActions;
import org.example.utils.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //locators
    By todaysDeals = By.cssSelector("a[data-csa-c-content-id='nav_cs_gb']");
    By cart = By.id("nav-cart");
    By accountAndLists = By.id("nav-link-accountList");
    By yourOrders = By.id("nav_prefetch_yourorders");
    By yourAddress = By.id("nav_prefetch_youraddresses");
    By yourLists = By.cssSelector("a[href*='wishlist']");
    By signInHeader = By.cssSelector("h1.a-size-medium-plus.a-spacing-small");

    //actions
    @Step("Navigate to Amazon Home page")
    public void navigateToHomePage() {
        BrowserActions.navigateToUrl(driver, "https://www.amazon.eg/");
    }

    @Step("Click on Today's Deals")
    public HomePage clickOnTodaysDeals() {
        ElementsActions.clickElement(driver, todaysDeals);
        return this;
    }

    @Step("Click on Cart")
    public HomePage clickOnCart() {
        ElementsActions.clickElement(driver, cart);
        return this;
    }

    @Step("Click on Your Orders")
    public HomePage clickOnYourOrders() {
        ElementsActions.hoverOnElement(driver, accountAndLists);
        ElementsActions.clickElementFromList(driver, yourOrders);
        return this;
    }

    @Step("Click on Your Address")
    public HomePage clickOnYourAddress() {
        ElementsActions.hoverOnElement(driver, accountAndLists);
        ElementsActions.clickElementFromList(driver, yourAddress);
        return this;
    }

    @Step("Click on Your Lists")
    public HomePage clickOnYourLists() {
        ElementsActions.hoverOnElement(driver, accountAndLists);
        ElementsActions.clickElementFromList(driver, yourLists);
        return this;
    }

    //assertions
    @Step("Verify user is not logged in")
    public HomePage verifyUserIsNotLoggedIn() {
        Validations.validateEqual(ElementsActions.getText(driver, signInHeader), "Sign in or create account","You are signed in");
        return this;
    }

    public HomePage verifyUserIsRedirectedToListPage() {
        Validations.validatePageUrl(driver, "https://www.amazon.eg/hz/wishlist/intro");
        return this;
    }

}
