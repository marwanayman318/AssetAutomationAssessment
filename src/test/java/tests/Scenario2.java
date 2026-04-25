package tests;

import io.qameta.allure.Description;
import org.example.drivers.DriverManager;
import org.example.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static data.DataProvider.QTY;

@Listeners(org.example.listeners.BaseListeners.class)
public class Scenario2 extends BaseTest {

    private String expectedName;
    private String expectedPrice;
    private String expectedSubTotal;

    private WebDriver driver;
    private HomePage homePage;
    private TodaysDealsPage todaysDealsPage;
    private ProductPage productPage;
    private ProductCheckPage productCheckPage;
    private CartPage cartPage;


    @BeforeMethod
    public void navigateToProduct() {

        driver = DriverManager.getDriver();

        homePage = new HomePage(driver);
        todaysDealsPage = new TodaysDealsPage(driver);
        productPage = new ProductPage(driver);
        productCheckPage = new ProductCheckPage(driver);
        cartPage = new CartPage(driver);

        homePage.clickOnTodaysDeals();
        todaysDealsPage.clickOnSecondCategory().clickOnFirstItem();

        expectedName  = productPage.getFullProductName();
        expectedPrice = productPage.getExpectedProductPrice();
    }

    @Test(priority = 1)
    @Description("Verify product name and price are displayed correctly on the product page")
    public void verifyProductDetailsOnProductPage() {
        productPage.validateProductDetails(expectedName, expectedPrice);
    }
    @Test(priority = 2)
    @Description("Verify the 'Added to Cart' confirmation appears after adding the product")
    public void verifyProductAddedToCart() {
        productPage
                .selectQuantity(QTY)
                .clickOnSecondVariant()
                .clickOnAddToCartButton()
                .clickNoThanks()
                .validateProductAddedToCart();
    }

    @Test(priority = 3)
    @Description("Verify product name, price, quantity and subtotal are correct in the cart")
    public void verifyCartDetails() {
        productPage
                .selectQuantity(QTY)
                .clickOnSecondVariant()
                .clickOnAddToCartButton()
                .clickNoThanks();

        expectedSubTotal = productCheckPage.getExpectedSubTotal();

        productCheckPage.validateProductAddedToCart()
                .clickOnGoToBasket();

        cartPage.validateProductDetails(
                expectedName,
                expectedPrice,
                cartPage.getCartCount(),
                expectedSubTotal
        );
    }












}
