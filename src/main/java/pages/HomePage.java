package pages;

import base.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class HomePage extends PageBase {

    @FindBy(id = "signup-link")
    private WebElement signUpFreeButton;

    public HomePage() throws IOException {
        relativeUrl = "#";
    }

    @Override
    public boolean isInitialized() {
        return signUpFreeButton.isDisplayed();
    }
}
