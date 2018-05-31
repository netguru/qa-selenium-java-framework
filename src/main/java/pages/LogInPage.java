package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class LogInPage extends PageBase {

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

    public LogInPage(WebDriver driver) throws IOException {
        super(driver);

        relativeUrl = "sign_in";
    }

    @Override
    public boolean isInitialized() {
        return loginButton.isDisplayed() ? true : false;
    }

    public void logIn(String email, String password, boolean rememberMe) {
        provideEmail(email);
        providePassword(password);
        if(rememberMe)
            clickRememberMeCheckbox();
        clickLogInButton();
    }

    public void provideEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void providePassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickRememberMeCheckbox() {
        rememberMeCheckbox.click();
    }

    public void clickForgotPasswordButton() {
        forgotPasswordButton.click();
    }

    public void clickSignUpNowButton() {
        signUpButton.click();
    }

    public void clickLogInButton() {
        loginButton.click();
    }

    public String getAlertText() {
        return alertTextElement.getText();
    }
}
