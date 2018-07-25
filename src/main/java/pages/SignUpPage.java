package pages;

import base.BasePage;
import ngelements.NGTextInput;
import org.openqa.selenium.support.FindBy;

public final class SignUpPage extends BasePage {

    @FindBy(id = "user_email")
    private NGTextInput emailInput;

    public SignUpPage() {
        relativeUrl = "register/new";
    }

    @Override
    public boolean isInitialized() {
        return emailInput.isDisplayed();
    }
}
