package pages;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGCheckbox;
import ngelements.NGTextBlock;
import ngelements.NGTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//input[contains(@name, 'email')]")
    private NGTextInput emailInput;

    @FindBy(xpath = "//input[@name='password']")
    private NGTextInput passwordInput;

    @FindBy(xpath = "//input[contains(@name, 'passwordConfirmation')]")
    private NGTextInput passwordConfirmationInput;

    @FindBy(xpath = "//input[@name='termsAgreement']")
    private NGCheckbox termsAgreementForEmailRegistrationCheckbox;

    @FindBy(xpath = "//button[contains(@type, 'submit')]")
    private NGButton createAnAccountButton;

    @FindBy(xpath = "//h4[contains(@class, 'H4-itykkt')]")
    private NGTextBlock emailConfirmationMessage;

    public RegisterPage(WebDriver driver) {
        super(driver, "/register");
    }

    @Override
    public boolean isInitialized() {
        try {
            return passwordConfirmationInput.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void registerUser(String email, String password, String passwordConfirmation, boolean termsAgreement) {
        provideEmail(email);
        providePassword(password);
        providePasswordConfirmation(passwordConfirmation);
        if (termsAgreement) {
            checkTermsAgreementForEmailRegistrationCheckbox();
        }
        clickCreateAnAccountButton();
    }

    public void registerUser(String email, String password) {
        registerUser(email, password, password, true);
    }

    public void provideEmail(String email) {
        emailInput.sendVulnerableData(email);
    }

    public void providePassword(String password) {
        passwordInput.sendVulnerableData(password);
    }

    public void providePasswordConfirmation(String passwordConfirmation) {
        passwordConfirmationInput.sendVulnerableData(passwordConfirmation);
    }

    public void checkTermsAgreementForEmailRegistrationCheckbox() {
        termsAgreementForEmailRegistrationCheckbox.moveToElementAndClick();
    }

    public void clickCreateAnAccountButton() {
        createAnAccountButton.click();
    }

    public String getEmailErrorMessage() {
        return emailInput.getValidator().getText();
    }

    public String getPasswordErrorMessage() {
        return passwordInput.getValidator().getText();
    }

    public String getPasswordConfirmationErrorMessage() {
        return passwordConfirmationInput.getValidator().getText();
    }

    public String getTermsAgreementForEmailRegistrationErrorMessage() {
        return termsAgreementForEmailRegistrationCheckbox.getValidator().getText();
    }

    public boolean isEmailConfirmationMessageVisible() {
        return emailConfirmationMessage.isDisplayed();
    }
}
