package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class SignUpPage extends PageBase {

    @FindBy(id = "user_email")
    private WebElement emailInput;

    public SignUpPage() throws IOException {
        relativeUrl = "register/new";
    }

    @Override
    public boolean isInitialized() {
        return emailInput.isDisplayed();
    }
}
