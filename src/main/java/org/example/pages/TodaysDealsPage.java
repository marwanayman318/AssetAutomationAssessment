package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.ElementsActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.example.utils.Waits.waitUntilVisible;

public class TodaysDealsPage extends BasePage{


    public TodaysDealsPage(WebDriver driver) {
        super(driver);
    }

    //locators
    By secondCategory = By.id("discount-bubble-discounts-collection-deals-ending-today");
    By itemContainer = By.cssSelector("div[data-testid='product-card']");


    //actions
    @Step("Click on the second category of today's deals")
    public TodaysDealsPage clickOnSecondCategory() {
        ElementsActions.clickElement(driver,secondCategory);
        return this;
    }
    @Step("Click on the first item in the list of today's deals")
    public TodaysDealsPage clickOnFirstItem() {
        waitUntilVisible(driver,itemContainer);
        List<WebElement> allCards = driver.findElements(itemContainer);
        allCards.get(0).click();
        return this;
    }

    //assertions



}
