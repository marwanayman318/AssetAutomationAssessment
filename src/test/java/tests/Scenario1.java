package tests;

import org.example.drivers.DriverManager;
import org.example.listeners.BaseListeners;
import org.example.pages.LoginPage;
import org.testng.annotations.*;

import java.io.File;

import static data.DataProvider.UNREGISTERED_EMAIL;

@Listeners(BaseListeners.class)
public class Scenario1 extends BaseTest {

    //Tests
    @Test
    public void testScenario1() {
        new LoginPage(DriverManager.getDriver())
                .goToLoginPage().enterEmail(UNREGISTERED_EMAIL)
                .clickContinueButton()
                .verifyUserIsNotRegistered();
    }

}
