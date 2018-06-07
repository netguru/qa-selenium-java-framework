package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class LogInPage extends PageBase {

    @FindBy(id = "user_login")
    private WebElement emailInput;

    @FindBy(id = "user_password")
    private WebElement passwordInput;

    @FindBy(css = ".checkbox__button")
    private WebElement rememberMeCheckbox;

    @FindBy(css = ".pull-right")
    private WebElement forgotPasswordButton;

    @FindBy(id = "login-btn")
    private WebElement loginButton;

    @FindBy(id = "signup-link")
    private WebElement signUpButton;

    @FindBy(css = ".flash-message")
    private WebElement alertTextElement;

    public LogInPage(WebDriver driver) throws IOException {
        super(driver);

        relativeUrl = "sign_in";
    }

    @Override
    public boolean isInitialized() {
        return loginButton.isDisplayed();
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
