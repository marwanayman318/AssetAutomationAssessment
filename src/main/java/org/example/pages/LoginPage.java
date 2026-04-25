package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.ElementsActions;
import org.example.utils.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //locators
    private final By clickOnSignup = By.className("nav-line-1-container");
    private final By email = By.id("ap_email_login");
    private final By password = By.id("ap_password");
    private final By continueBtn = By.className("a-button-input");
    private final By signInButton = By.id("signInSubmit");
    private final By message = By.cssSelector(".a-size-medium-plus.a-spacing-small");
    private final By emailExists = By.className("a-form-label");
    private final By invalidPasswordMessage = By.className("a-list-item");


    //actions
    @Step("Go To Login Page")
    public LoginPage goToLoginPage() {
        ElementsActions.clickElement(driver, clickOnSignup);
        return this;
    }

    @Step("Enter email: {email}")
    public LoginPage enterEmail(String email) {
        ElementsActions.sendData(driver, this.email, email);
        return this;
    }

    @Step("Enter password: {password}")
    public LoginPage enterPassword(String password) {
        ElementsActions.sendData(driver, this.password, password);
        return this;
    }

    @Step("Click Continue button")
    public LoginPage clickContinueButton() {
        ElementsActions.clickElement(driver, continueBtn);
        return this;
    }

    @Step("Click Sign In button")
    public LoginPage clickSignInButton() {
        ElementsActions.clickElement(driver, signInButton);
        return this;
    }

    @Step("Check if email exists ")
    public LoginPage emailExists() {
        ElementsActions.findElement(driver, password);
        return this;
    }

    @Step("Check if invalid password message is displayed")
    public String getInvalidPasswordMessage() {
        return ElementsActions.getText(driver, invalidPasswordMessage);
    }

    @Step("Check if successful login message is displayed")
    public String getMessage() {
        return ElementsActions.getText(driver, message);
    }

    //assertions
    @Step("Verify user is registered")
    public LoginPage verifyUserIsRegistered() {
        Validations.validatePageTitle(driver, "Amazon Sign-In", "There is no such title");
        return this;
    }

    @Step("Verify user is not registered")
    public LoginPage verifyUserIsNotRegistered() {
        Validations.validateEqual( getMessage(),"Looks like you're new to Amazon", "The user is registered");
        return this;
    }

    @Step("Verify invalid password error message")
    public LoginPage verifyInvalidPassword() {
        Validations.validateEqual(getMessage(), "Your password is incorrect", "The credentials are correct");
        return this;
    }

    @Step("Verify successful login")
    public LoginPage verifySuccessfulLogin() {
        Validations.validateEqual(getMessage(), "Sign in successful", "The login was not successful");
        return this;
    }
}
