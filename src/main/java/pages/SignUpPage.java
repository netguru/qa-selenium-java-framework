package pages;

import base.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends PageBase {

    @FindBy(id = "user_email")
    private WebElement emailInput;

    public SignUpPage() {
        relativeUrl = "register/new";
    }

    @Override
    public boolean isInitialized() {
        return emailInput.isDisplayed();
    }
}
