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
        if (rememberMe) {
            clickRememberMeCheckbox();
        }
        clickLogInButton();
    }

    public void logInUserAndRememberMe(UserType userType, boolean rememberMe) {
        Properties props = UtilitiesFunctions.loadProperties();

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
    }

    public void providePassword(String password) {
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
