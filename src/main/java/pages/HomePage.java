package pages;

import base.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class HomePage extends PageBase {

    @FindBy(id = "signup-link")
    private WebElement signUpFreeButton;

    public HomePage() {
        relativeUrl = "#";
    }

    @Override
    public boolean isInitialized() {
        return signUpFreeButton.isDisplayed();
    }
}
