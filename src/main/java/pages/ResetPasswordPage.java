package pages;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGTextBlock;
import ngelements.NGTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ResetPasswordPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class, 'styled__Message-sc-1mxkpa2-3')]")
    private NGTextBlock styledMessage;

    @FindBy(xpath = "//*[contains(@name, 'email')]")
    private NGTextInput emailInput;

    @FindBy(xpath = "//*[contains(@type, 'submit')]")
    private NGButton submitButton;

    @FindBy(xpath = "//*[contains(@class, 'Input__Error')]")
    public NGTextBlock emailErrorMessage;

    public ResetPasswordPage(WebDriver driver) {
        super(driver, "/reset-password");
    }

    @Override
    public boolean isInitialized() {
        return styledMessage.isDisplayed();
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
        return emailErrorMessage.getText();
    }
}
