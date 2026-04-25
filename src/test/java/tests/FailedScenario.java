package tests;

import org.example.drivers.DriverManager;
import org.example.pages.LoginPage;
import org.testng.annotations.*;

import static data.DataProvider.REGISTERED_EMAIL;

@Listeners(org.example.listeners.BaseListeners.class)
public class FailedScenario extends BaseTest {

    @Test
    public void testFailedScenario() {
             new LoginPage(DriverManager.getDriver())
                     .goToLoginPage().enterEmail(REGISTERED_EMAIL)
                     .clickContinueButton()
                     .verifyUserIsRegistered();
     }

}
