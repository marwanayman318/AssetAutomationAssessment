package org.example.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementsActions {

    private ElementsActions() {
        // Private constructor to prevent instantiation
    }

    @Step("Sending data: '{data}' to element located by: {locator}")
    public static void sendData(WebDriver driver, By locator, String data) {
        Waits.waitUntilVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        driver.findElement(locator).clear();
        findElement(driver,locator).sendKeys(data);
        LogsUtil.info("Sent data: '" + data + "' to element located by: " , locator.toString() , "Text: " , findElement(driver,locator).getText());
    }

    @Step("Clicking on element located by: {locator}")
    public static void clickElement(WebDriver driver, By locator) {
        Waits.waitUntilClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        WebElement element = findElement(driver, locator);
        String text = element.getText();
        element.click();
        LogsUtil.info("Clicked element: ", locator.toString(), " | Text: ", text);
    }

    @Step("Retrieving text from element located by: {locator}")
    public static String getText(WebDriver driver, By locator) {
        Waits.waitUntilVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        LogsUtil.info("Retrieved text from element located by: " , locator.toString(), "Text: ", findElement(driver,locator).getText());
        return findElement(driver,locator).getText();
    }

    //find element
    @Step("Finding element located by: {locator}")
    public static WebElement findElement(WebDriver driver, By locator) {

        return driver.findElement(locator);
    }

    //hover On Element
    @Step("Hovering on element located by: {locator}")
    public static Actions hoverOnElement(WebDriver driver, By locator) {
        Waits.waitUntilVisible(driver, locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).perform();
        String text = findElement(driver,locator).getText();
        LogsUtil.info("Hovering on element located by: " , locator.toString(), "Text: ",text);
        return actions;
    }

    //click on element from the list
    @Step("Clicking on element located by: {locator}")
    public static void clickElementFromList(WebDriver driver, By locator) {
        Waits.waitUntilClickable(driver, locator);
        WebElement element = findElement(driver, locator);
        String text = element.getText();
        element.click();
        LogsUtil.info("Clicked on element located by: " , locator.toString(), "Text: ", text);
    }

}
