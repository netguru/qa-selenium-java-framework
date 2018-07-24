package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.UserType;
import utilities.UtilitiesFunctions;

import java.util.Properties;

public final class LogInPage extends BasePage {

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
        String password = props.getProperty("COMMON_PASSWORD");

        switch (userType) {
            case ADMIN:
                email = props.getProperty("ADMIN_EMAIL");
                break;
            case PROVIDER:
                email = props.getProperty("PROVIDER_EMAIL");
                break;
            case CONSUMER_PAID:
                email = props.getProperty("PAID_CONSUMER_EMAIL");
                break;
            case CONSUMER_UNPAID:
                email = props.getProperty("UNPAID_CONSUMER_EMAIL");
                break;
            case CONSUMER_SPECIAL:
                email = props.getProperty("SPECIAL_CONSUMER_EMAIL");
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
        rememberMeCheckbox.click();
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
