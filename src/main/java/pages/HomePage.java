package pages;

import base.BasePage;
import ngelements.NGButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public final class HomePage extends BasePage {

    @FindBy(id = "signup-link")
    private NGButton signUpFreeButton;

    public HomePage(WebDriver driver) {
        super(driver);
        relativeUrl = "#";
    }

    @Override
    public boolean isInitialized() {
        return signUpFreeButton.isDisplayed();
    }
}
