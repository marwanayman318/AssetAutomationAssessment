package org.example.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;

public class BrowserFactory {
    public static WebDriver getDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions options = getChromeOptions();
                return new ChromeDriver(options);
            case "firefox":
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                return new FirefoxDriver(firefoxOptions);
            default:
                EdgeOptions EdgeOptionsoptions = getEdgeOptions();
                return new EdgeDriver(EdgeOptionsoptions);
        }
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("start-maximized");
        firefoxOptions.addArguments("disable-infobars");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--remote-allow-origins=*");
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        firefoxOptions.setAcceptInsecureCerts(true);
        return firefoxOptions;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions EdgeOptions = new EdgeOptions();
        EdgeOptions.addArguments("--disable-notifications");
        EdgeOptions.addArguments("start-maximized");
        EdgeOptions.addArguments("disable-infobars");
        EdgeOptions.addArguments("--disable-extensions");
        EdgeOptions.addArguments("--remote-allow-origins=*");

        Map<String, Object> EdgePrefs = Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill_profile_enabled", false);

        EdgeOptions.setExperimentalOption("prefs", EdgePrefs);
        EdgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return EdgeOptions;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");

        Map<String, Object> prefs = Map.of(
             "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill_profile_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return options;
    }
}
