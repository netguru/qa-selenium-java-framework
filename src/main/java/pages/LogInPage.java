package pages;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGCheckbox;
import ngelements.NGTextBlock;
import ngelements.NGTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import utilities.UserType;

public final class LogInPage extends BasePage {

    @FindBy(id = "user_login")
    private NGTextInput emailInput;

    @FindBy(id = "user_password")
    private NGTextInput passwordInput;

    @FindBy(css = ".checkbox__button")
    private NGCheckbox rememberMeCheckbox;

    @FindBy(css = ".pull-right")
    private NGButton forgotPasswordButton;

    @FindBy(id = "login-btn")
    private NGButton loginButton;

    @FindBy(id = "signup-link")
    private NGButton signUpButton;

    @FindBy(css = ".flash-message")
    private NGTextBlock alertTextElement;

    public LogInPage(WebDriver driver) {
        super(driver);
        relativeUrl = "sign_in";
    }

    @Override
    public boolean isInitialized() {
        return loginButton.isDisplayed();
    }

    public void logInUserAndRememberMe(String email, String password, boolean rememberMe) {
        provideEmail(email);
        providePassword(password);
        if (rememberMe) {
            clickRememberMeCheckbox();
        }
        clickLogInButton();
    }

    public void logInUserAndRememberMe(UserType userType, boolean rememberMe) {

        String email;
        String password = propertiesLoader.getCommonPassword();

        email = propertiesLoader.getEmailByUserType(userType);

        logInUserAndRememberMe(email, password, rememberMe);
    }

    public void provideEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void providePassword(String password) {
        //TODO create function for protected data in NGTextInput
        passwordInput.sendKeys(password);
    }

    public void clickRememberMeCheckbox() {
        rememberMeCheckbox.select();
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
