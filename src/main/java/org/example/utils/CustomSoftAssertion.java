package org.example.utils;

import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class CustomSoftAssertion extends SoftAssert {

    public static CustomSoftAssertion softAssert = new CustomSoftAssertion();

    public static void CustomAssertAll(ITestResult result) {
        try {
            softAssert.assertAll("Custom Soft Assertion");
        } catch (Exception e) {
            LogsUtil.error("Soft assertion failed: " + e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        }
        finally {
            reInitializeSoftAssertion();
        }
    }

    private static void reInitializeSoftAssertion() {
        softAssert = new CustomSoftAssertion();
    }
}
