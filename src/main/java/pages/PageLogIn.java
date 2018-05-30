package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class PageLogIn extends PageBase {

    @FindBy(id = "user_login")
    WebElement emailInput;

    @FindBy(id = "user_password")
    WebElement passwordInput;

    @FindBy(css = ".checkbox__button")
    WebElement rememberMeCheckbox;

    @FindBy(css = ".pull-right")
    WebElement forgotPasswordButton;

    @FindBy(id = "login-btn")
    WebElement loginButton;

    @FindBy(id = "signup-link")
    WebElement signUpButton;

    @FindBy(css = ".flash-message")
    WebElement alertTextElement;

    public PageLogIn(WebDriver driver) throws IOException {
        super(driver);

        relativeUrl = "sign_in";
    }

    @Override
    public boolean isInitialized() {
        return loginButton.isDisplayed() ? true : false;
    }

    public void logIn(String email, String password, boolean rememberMe) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        if(rememberMe)
            rememberMeCheckbox.click();
        loginButton.click();
    }

    public void clickForgotPasswordButton() {
        forgotPasswordButton.click();
    }

    public void clickSignUpNowButton() {
        signUpButton.click();
    }

    public String getAlertText() {
        return alertTextElement.getText();
    }
}
