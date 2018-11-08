package pages;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGCheckbox;
import ngelements.NGTextBlock;
import ngelements.NGTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class, 'H1-sc-4ckksg-0')]")
    private NGTextBlock pageTitle;

    @FindBy(xpath = "//*[contains(@name, 'email')]")
    private NGTextInput emailInput;

    @FindBy(xpath = "//*[@name='password']")
    private NGTextInput passwordInput;

    @FindBy(xpath = "//*[contains(@name, 'passwordConfirmation')]")
    private NGTextInput passwordConfirmationInput;

    @FindBy(xpath = "//*[@name='termsAgreement']")
    private NGButton termsAgreementForEmailRegistrationCheckbox;

    @FindBy(xpath = "//*[contains(@type, 'submit')]")
    private NGButton createAnAccountButton;

    @FindBy(xpath = "(//*[contains(@class, 'sc-bwzfXH hjVfwW')])[1]/*[contains(@class, 'Input__Error')]")
    private NGTextBlock emailErrorMessage;

    @FindBy(xpath = "(//*[contains(@class, 'sc-bwzfXH hjVfwW')])[2]/*[contains(@class, 'Input__Error')]")
    private NGTextBlock passwordErrorMessage;

    @FindBy(xpath = "(//*[contains(@class, 'sc-bwzfXH hjVfwW')])[3]/*[contains(@class, 'Input__Error')]")
    private NGTextBlock passwordConfirmationErrorMessage;

    @FindBy(xpath = "//*[contains(@class, 'Checkbox__Error')]")
    private NGTextBlock termsAgreementForEmailRegistrationErrorMessage;

    public RegisterPage(WebDriver driver) {
        super(driver, "/register");
    }

    @Override
    public boolean isInitialized() {
        return passwordConfirmationInput.isDisplayed();
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

    public boolean isPageTitleEqual(String title) {
        return title.equals(pageTitle.getText());
    }

    public String getEmailErrorMessage() {
        return emailErrorMessage.getText();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage.getText();
    }

    public String getPasswordConfirmationErrorMessage() {
        return passwordConfirmationErrorMessage.getText();
    }

    public String getTermsAgreementForEmailRegistrationErrorMessage() {
        return termsAgreementForEmailRegistrationErrorMessage.getText();
    }
}
