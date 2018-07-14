package pages;

import base.BasePage;
import ngelements.Button;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

public final class ForgotPasswordPage extends BasePage {

    @Name("submit button")
    @FindBy(xpath = "//input[@type='submit']")
    private Button submitButton;

    public ForgotPasswordPage() {
        relativeUrl = "forgot_passwords/new";
    }

    @Override
    public boolean isInitialized() {
        return submitButton.isDisplayed();
    }
}
