package pages;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGTextBlock;
import ngelements.NGTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ResetPasswordPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'Message')]")
    private NGTextBlock styledMessage;

    @FindBy(xpath = "//input[contains(@name, 'email')]")
    private NGTextInput emailInput;

    @FindBy(xpath = "//button[contains(@type, 'submit')]")
    private NGButton submitButton;

    public ResetPasswordPage(WebDriver driver) {
        super(driver, "/reset-password");
    }

    @Override
    public boolean isInitialized() {
        try {
            return styledMessage.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void submitEmail(String email) {
        provideEmail(email);
        clickSubmitButton();
    }

    public void provideEmail(String email) {
        emailInput.sendVulnerableData(email);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public String getEmailErrorMessage() {
        return emailInput.getValidator().getText();
    }
}
