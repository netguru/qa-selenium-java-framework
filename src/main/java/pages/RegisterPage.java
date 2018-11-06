package pages;

import base.BasePage;
import ngelements.NGCheckbox;
import ngelements.NGTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//*[contains(@name, 'passwordConfirmation')]")
    private NGTextInput passwordConfirmationInput;

    @FindBy(xpath = "//*[@name='termsAgreement']")
    private NGCheckbox termsAgreementForEmailRegistrationCheckbox;

    public RegisterPage(WebDriver driver) {
        super(driver, "/register");
    }

    @Override
    public boolean isInitialized() {
        return passwordConfirmationInput.isDisplayed();
    }
}
