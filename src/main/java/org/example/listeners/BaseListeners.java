package org.example.listeners;

import org.example.drivers.DriverManager;
import org.example.utils.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.*;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import java.io.File;


public class BaseListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {

    File allureResultsDir = new File("test-outputs/allure-results");
    File logs = new File("test-outputs/logs");
    File screenShots = new File("test-outputs/screenShots");

    @Override
    public void onExecutionStart() {
        LogsUtil.info("Test execution started. Cleaning up old Allure results...");


        LogsUtil.info( "Test execution started.");
        FileUtils.deleteFiles(allureResultsDir);
        FileUtils.cleanDirectory(logs);
        FileUtils.deleteFiles(screenShots);
    }

    @Override
    public void onExecutionFinish() {
        LogsUtil.info("Test execution finished. Allure results are ready in: " + allureResultsDir.getAbsolutePath());

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            CustomSoftAssertion.CustomAssertAll(testResult);
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("window.scrollTo(0, 0);");
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS:
                    ScreenshotsUtils.takeScreenshot("passed-"+testResult.getName());
                    break;
                case ITestResult.FAILURE:
                    ScreenshotsUtils.takeScreenshot("failed-"+testResult.getName());
                    break;
                case ITestResult.SKIP:
                    ScreenshotsUtils.takeScreenshot("skipped-"+testResult.getName());
                    break;
            }
            AllureUtils.attachLogsToAllureReport();
        }
    }

}
