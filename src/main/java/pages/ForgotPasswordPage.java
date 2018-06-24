package pages;

import base.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class ForgotPasswordPage extends PageBase {

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    public ForgotPasswordPage() throws IOException {
        relativeUrl = "forgot_passwords/new";
    }

    @Override
    public boolean isInitialized() {
        return submitButton.isDisplayed();
    }
}
