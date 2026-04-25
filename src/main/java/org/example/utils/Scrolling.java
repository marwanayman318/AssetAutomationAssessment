package org.example.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class Scrolling {

    private Scrolling() {
        // Private constructor to prevent instantiation
    }

    @Step("Scrolling to element: {0}")
    public static void scrollToElement(WebDriver driver, By locator) {
        LogsUtil.info("Scrolling to element located by: " + locator.toString());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ElementsActions.findElement(driver,locator));

    }
}
