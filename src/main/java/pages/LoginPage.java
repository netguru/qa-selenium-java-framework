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

    @FindBy(xpath = "//*[contains(@type, 'email')]")
    private NGTextInput emailInput;

    @FindBy(xpath = "//*[contains(@type, 'password')]")
    private NGTextInput passwordInput;

    @FindBy(xpath = "//*[contains(@type, 'submit')]")
    private NGButton logInButton;

    @FindBy(xpath = "//*[contains(@class, 'styled__ForgetPasswordLink-sc-1mxkpa2-1')]")
    private NGButton forgotYourPasswordButton;

    @FindBy(xpath = "//*[contains(@class, 'Button__ButtonAnchor-sc-1emfup8-1')]")
    private NGButton createAnAccountButton;

    @FindBy(xpath = "(//*[contains(@class, 'bwzfXH hjVfwW')])[1]/*[contains(@class, 'Input__Error-sc-1cfdsff-3')]")
    private NGTextBlock emailErrorMessage;

    @FindBy(xpath = "(//*[contains(@class, 'bwzfXH hjVfwW')])[2]/*[contains(@class, 'Input__Error-sc-1cfdsff-3')]")
    private NGTextBlock passwordErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver, "/login");
    }

    @Override
    public boolean isInitialized() {
        return forgotYourPasswordButton.isDisplayed();
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
