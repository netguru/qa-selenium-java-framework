package pages;

import base.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class ForgotPasswordPage extends PageBase {

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    public ForgotPasswordPage() {
        relativeUrl = "forgot_passwords/new";
    }

    @Override
    public boolean isInitialized() {
        return submitButton.isDisplayed();
    }
}
