package tests;

import org.example.drivers.DriverManager;
import org.example.pages.HomePage;
import org.example.utils.BrowserActions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static data.DataProvider.driverName;

public class BaseTest {
    //configuration
    @BeforeClass
    public void setup() {
        // Initialize the WebDriver
        DriverManager.createInstance(driverName);
        new HomePage(DriverManager.
                getDriver())
                .navigateToHomePage();
    }

    @AfterClass
    public void tearDown() {
        BrowserActions.quitBrowser(DriverManager.getDriver());
    }
}
