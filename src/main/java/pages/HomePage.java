package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.io.IOException;

public class HomePage extends PageBase {

    @FindBy(id = "signup-link")
    private WebElement signUpFreeButton;

    public HomePage(WebDriver driver) throws IOException {
        super(driver);

        relativeUrl = "#";
    }

    @Override
    public boolean isInitialized() {
        return signUpFreeButton.isDisplayed();
    }
}
