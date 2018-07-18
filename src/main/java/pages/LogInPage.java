package pages;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGCheckbox;
import ngelements.NGTextBlock;
import ngelements.NGTextInput;
import org.openqa.selenium.support.FindBy;
import utilities.UserType;
import utilities.UtilitiesFunctions;

import java.util.Properties;

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

    public LogInPage() {
        relativeUrl = "sign_in";
    }

    @Override
    public boolean isInitialized() {
        return loginButton.isDisplayed();
    }

    public void logInUserAndRememberMe(String email, String password, boolean rememberMe) {
        provideEmail(email);
        providePassword(password);
        if (rememberMe)
            clickRememberMeCheckbox();
        clickLogInButton();
    }

    public void logInUserAndRememberMe(UserType userType, boolean rememberMe) {
        Properties props = UtilitiesFunctions.loadFile("initConfig.properties");

        String email = "";
        String password = props.getProperty("common_password");

        switch (userType) {
            case ADMIN:
                email = props.getProperty("admin_email");
                break;
            case PROVIDER:
                email = props.getProperty("provider_email");
                break;
            case CONSUMER_PAID:
                email = props.getProperty("paid_consumer_email");
                break;
            case CONSUMER_UNPAID:
                email = props.getProperty("unpaid_consumer_email");
                break;
            case CONSUMER_SPECIAL:
                email = props.getProperty("special_consumer_email");
                break;
            default:
                log.error("Wrong UserType. Accepted values are: ADMIN, PROVIDER, CONSUMER_PAID" +
                        "CONSUMER_UNPAID, CONSUMER_SPECIAL");
                break;
        }

        logInUserAndRememberMe(email, password, rememberMe);
    }

    public void provideEmail(String email) {
        emailInput.sendKeys(email);
        log.info("Providing email: " + email);
    }

    public void providePassword(String password) {
        passwordInput.sendKeys(password);
        log.info("Providing password: " + password);
    }

    public void clickRememberMeCheckbox() {
        rememberMeCheckbox.select();
        log.info("Selecting Remember Me checkbox");
    }

    public void clickForgotPasswordButton() {
        forgotPasswordButton.click();
        log.info("Selecting Forgot Password? button");
    }

    public void clickSignUpNowButton() {
        signUpButton.click();
        log.info("Selecting Sign Up Now button");
    }

    public void clickLogInButton() {
        loginButton.click();
        log.info("Selecting Log In button");
    }

    public String getAlertText() {
        return alertTextElement.getText();
    }
}
