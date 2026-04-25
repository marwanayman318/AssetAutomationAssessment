package org.example.utils;


import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {

    public static final String ALLURE_REPORT_PATH = "test-outputs/allure-results";
    private AllureUtils() {
        // Private constructor to prevent instantiation
    }

    public static void attachLogsToAllureReport() {
        try {
            File logFile = FileUtils.getLatestFile(LogsUtil.LOGS_PATH);
            if (!logFile.exists()) {
                LogsUtil.warn("Log file does not exist: " + LogsUtil.LOGS_PATH);
                return;
            }
            Allure.addAttachment("Logs File", Files.readString(Path.of(logFile.getPath())));
            LogsUtil.info("Attached logs to Allure report");
        } catch (Exception e) {
            LogsUtil.error("Error attaching logs to Allure report: " + e.getMessage());
        }
    }

    public static void attachScreenshotToAllure(String screenshotName, String screenshotPath) {
        try {
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotPath)));
        } catch (Exception e) {
            LogsUtil.error("Error attaching screenshot to Allure report: " + e.getMessage());
        }
    }

}
