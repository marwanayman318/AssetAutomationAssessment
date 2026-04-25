package org.example.drivers;

import io.qameta.allure.Step;
import org.example.utils.LogsUtil;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
        super();
    }

    @Step("Creating WebDriver instance for browser: {browserName}")
    public static WebDriver createInstance(String browserName) {
        WebDriver driver = BrowserFactory.getDriver(browserName);
        LogsUtil.info("Created WebDriver instance for browser: " + browserName);
        setDriver(driver);
        return getDriver();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            fail("driver is null");
        }
        return driverThreadLocal.get();
    }

}
