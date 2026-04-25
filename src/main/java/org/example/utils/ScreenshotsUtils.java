package org.example.utils;


import org.apache.commons.io.FileUtils;
import org.example.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenshotsUtils {

    private static final String SCREENSHOT_PATH = "test-outputs/screenshots/";
    private ScreenshotsUtils() {
        // Private constructor to prevent instantiation
    }

    public static void takeScreenshot(String screenshotName) {
        try {
            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOT_PATH + screenshotName + ".png");
            FileUtils.copyFile(screenshot, screenshotFile);
            AllureUtils.attachScreenshotToAllure(screenshotName, screenshotFile.getPath());
        } catch (Exception e) {
            LogsUtil.error("Error taking screenshot: " + e.getMessage());
        }
    }
}
