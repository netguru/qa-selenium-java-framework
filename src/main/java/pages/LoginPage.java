package pages;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGTextBlock;
import ngelements.NGTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import utilities.UserType;
import utilities.UtilitiesFunctions;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[contains(@type, 'email')]")
    private NGTextInput emailInput;

    @FindBy(xpath = "//input[contains(@type, 'password')]")
    private NGTextInput passwordInput;

    @FindBy(xpath = "//button[contains(@type, 'submit')]")
    private NGButton logInButton;

    @FindBy(xpath = "//a[contains(@class, 'ForgetPasswordLink')]")
    private NGButton forgotYourPasswordButton;

    @FindBy(xpath = "//a[contains(@class, 'ButtonAnchor')]")
    private NGButton createAnAccountButton;

    @FindBy(xpath = "//div[contains(@class, 'bwzfXH hjVfwW')][1]/span[contains(@class, 'Input__Error')]")
    private NGTextBlock emailErrorMessage;

    @FindBy(xpath = "//div[contains(@class, 'bwzfXH hjVfwW')][2]/span[contains(@class, 'Input__Error')]")
    private NGTextBlock passwordErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver, "/login");
    }

    @Override
    public boolean isInitialized() {
        try {
            return forgotYourPasswordButton.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void logIn(String email, String password) {
        provideEmail(email);
        providePassword(password);
        clickLogInButton();
    }

    public void logIn(UserType userType) {
        logIn(UtilitiesFunctions.getUserEmail(userType), propertiesLoader.getCommonPassword());
    }

    public void provideEmail(String email) {
        emailInput.sendVulnerableData(email);
    }

    public void providePassword(String password) {
        passwordInput.sendVulnerableData(password);
    }

    public void clickLogInButton() {
        logInButton.click();
    }

    public void clickForgotYourPasswordButton() {
        forgotYourPasswordButton.click();
    }

    public void clickCreateAnAccountButton() {
        createAnAccountButton.click();
    }

    public String getEmailErrorMessage() {
        return emailErrorMessage.getText();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage.getText();
    }
}
