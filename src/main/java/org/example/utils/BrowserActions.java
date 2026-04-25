package org.example.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private BrowserActions() {
        // Private constructor to prevent instantiation
    }

    @Step("Navigating to URL: {url}")
    public static void navigateToUrl(WebDriver driver, String url) {
        driver.get(url);
        LogsUtil.info("Navigated to URL: " + url);
    }
    @Step("Getting current URL")
    public static String getCurrentUrl(WebDriver driver) {
        LogsUtil.info("Current URL is: " + driver.getCurrentUrl() + ".");
        return driver.getCurrentUrl();
    }

    //get page title
    @Step("Getting page title")
    public static String getTitle(WebDriver driver) {
        LogsUtil.info("Current page title is: " + driver.getTitle() + ".");
        return driver.getTitle();
    }

    @Step("Navigate back")
    public static void navigateBack(WebDriver driver) {
        driver.navigate().back();
        LogsUtil.info("Navigated back to previous page");
    }

    //refresh page
    @Step("Refreshing the page")
    public static void refresh(WebDriver driver) {
        LogsUtil.info("Refreshing the page.");
        driver.navigate().refresh();
    }

    //close browser
    @Step("Closing the browser")
    public static void close(WebDriver driver) {
        LogsUtil.info("Closing the browser.");
        driver.close();
    }

    //quit browser
    @Step("Quitting the browser")
    public static void quitBrowser(WebDriver driver) {
        LogsUtil.info("Quitting the browser.");
        driver.quit();
    }
}
