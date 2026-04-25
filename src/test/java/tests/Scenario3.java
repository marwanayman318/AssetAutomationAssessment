package tests;

import io.qameta.allure.Description;
import org.example.drivers.DriverManager;
import org.example.pages.HomePage;
import org.example.utils.BrowserActions;
import org.testng.annotations.*;

@Listeners(org.example.listeners.BaseListeners.class)
public class Scenario3 extends BaseTest {

    private HomePage homePage;
    @BeforeMethod
    public void goToHomePage() {
        homePage = new HomePage(DriverManager.getDriver());
        BrowserActions.navigateToUrl(DriverManager.getDriver(), "https://www.amazon.eg/");
    }

    @Test(priority = 1)
    @Description("Verify that Your Orders is not accessible without login")
    public void verifyYourOrdersRequiresLogin() {
        homePage.clickOnYourOrders()
                .verifyUserIsNotLoggedIn();
    }


    @Test(priority = 2)
    @Description("Verify that Your Addresses is not accessible without login")
    public void verifyYourAddressesRequiresLogin() {
        homePage.clickOnYourAddress()
                .verifyUserIsNotLoggedIn();
    }


    @Test(priority = 3)
    @Description("Verify that Your Lists is accessible without login")
    public void verifyYourListsIsAccessibleWithoutLogin() {
        homePage.clickOnYourLists()
                .verifyUserIsRedirectedToListPage();
    }

}