package pages;

import base.PageBase;
import base.UserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

    public void logIn(UserType userType, boolean rememberMe) throws IOException {
        Properties props = new Properties();
        props.load( new FileInputStream("initConfig.properties") );

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
                // TODO: Use a proper logger here
                System.out.println("Wrong UserType. Accepted values are: ADMIN, PROVIDER, CONSUMER_PAID" +
                        "CONSUMER_UNPAID, CONSUMER_SPECIAL");
                break;
        }

        logIn(email, password, rememberMe);
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
