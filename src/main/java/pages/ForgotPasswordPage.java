package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class ForgotPasswordPage extends PageBase {

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    public ForgotPasswordPage(WebDriver driver) throws IOException {
        super(driver);

        relativeUrl = "forgot_passwords/new";
    }

    @Override
    public boolean isInitialized() {
        return submitButton.isDisplayed();
    }
}
