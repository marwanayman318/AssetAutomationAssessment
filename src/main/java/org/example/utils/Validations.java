package org.example.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validations {

    private Validations() {
        // Private constructor to prevent instantiation
    }

    @Step("Validating condition is true")
    public static void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    @Step("Validating condition is false")
    public static void validateFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    @Step("Validating actual value: '{actual}' is equal to expected value: '{expected}'")
    public static void validateEqual(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    @Step("Validating actual value: '{actual}' is not equal to expected value: '{expected}'")
    public static void validateNotEqual(String actual, String expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }

    @Step("Validating Page URL: {expected}")
    public static void validatePageUrl(WebDriver driver, String expected) {
        Assert.assertEquals(BrowserActions.getCurrentUrl(driver), expected);
    }

    @Step("Validating Page Title: {expected}")
    public static void validatePageTitle(WebDriver driver, String expected,String message) {
        Assert.assertEquals(BrowserActions.getTitle(driver), expected , message);
    }
}
